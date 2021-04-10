package io.github.xbeeant.javamerge;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;
import io.github.xbeeant.javamerge.merger.ast.ImportDeclarationMerger;
import io.github.xbeeant.javamerge.merger.ast.ModifierMerger;
import io.github.xbeeant.javamerge.merger.ast.PackageDeclarationMerger;
import io.github.xbeeant.javamerge.merger.ast.expr.*;
import io.github.xbeeant.javamerge.merger.ast.stmt.BlockStmtMerger;
import io.github.xbeeant.javamerge.merger.ast.stmt.ExpressionStmtMerger;
import io.github.xbeeant.javamerge.merger.ast.stmt.ReturnStmtMerger;
import io.github.xbeeant.javamerge.merger.ast.stmt.StatementMerger;
import io.github.xbeeant.javamerge.merger.ast.type.*;
import io.github.xbeeant.javamerge.merger.body.FieldDeclarationMerger;
import io.github.xbeeant.javamerge.merger.body.MethodDeclarationMerger;
import io.github.xbeeant.javamerge.merger.body.ParameterMerger;
import io.github.xbeeant.javamerge.merger.comments.CommentMerger;
import io.github.xbeeant.javamerge.merger.comments.JavadocCommentMerger;
import io.github.xbeeant.javamerge.merger.comments.LineCommentMerger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽象的合并
 *
 * @author huangxiaobiao
 * @date 2020/10/15
 */
public abstract class AbstractNodeMerger<N extends Node> {
    public static final Logger logger = LoggerFactory.getLogger(AbstractNodeMerger.class);

    private static final ConcurrentHashMap<Class, AbstractNodeMerger> map = new ConcurrentHashMap<>();

    static {
        // ast
        map.put(PackageDeclaration.class, new PackageDeclarationMerger());
        map.put(ImportDeclaration.class, new ImportDeclarationMerger());
        map.put(Modifier.class, new ModifierMerger());

        // ast.type
        map.put(ClassOrInterfaceType.class, new ClassOrInterfaceTypeMerger());
        map.put(Type.class, new TypeMerger());
        map.put(ArrayType.class, new ArrayTypeMerger());
        map.put(VoidType.class, new VoidTypeMerger());
        map.put(TypeParameter.class, new TypeParameterMerger());
        map.put(PrimitiveType.class, new PrimitiveTypeMerger());

        // ast.expr
        map.put(AnnotationExpr.class, new AnnotationExprMerger());
        map.put(AssignExpr.class, new AssignExprMerger());
        map.put(BinaryExpr.class, new BinaryExprMerger());
        map.put(Expression.class, new ExpressionMerger());
        map.put(SimpleName.class, new SimpleNameMerger());
        map.put(MemberValuePair.class, new MemberValuePairMerger());
        map.put(MethodCallExpr.class, new MethodCallExprMerger());
        map.put(Name.class, new NameMerger());
        map.put(NormalAnnotationExpr.class, new NormalAnnotationExprMerger());
        map.put(VariableDeclarationExpr.class, new VariableDeclarationExprMerger());
        map.put(ReturnStmt.class, new ReturnStmtMerger());

        // ast.body
        map.put(FieldDeclaration.class, new FieldDeclarationMerger());
        map.put(MethodDeclaration.class, new MethodDeclarationMerger());
        map.put(Parameter.class, new ParameterMerger());
        // ast.stmt
        map.put(BlockStmt.class, new BlockStmtMerger());
        map.put(IfStmt.class, new IfStmtMerger());
        map.put(ExpressionStmt.class, new ExpressionStmtMerger());
        map.put(Statement.class, new StatementMerger());

        // comments
        map.put(Comment.class, new CommentMerger());
        map.put(LineComment.class, new LineCommentMerger());
        map.put(JavadocComment.class, new JavadocCommentMerger());
    }

    private boolean keepFirstWhenConflict;

    /**
     * 得到合并
     * 获得合并器
     *
     * @param clazz                 clazz
     * @param keepFirstWhenConflict 是否保持原有文件
     * @param <T>                   class extends Node
     * @return Node
     */
    public static <T extends Node> AbstractNodeMerger<T> getMerger(Class<T> clazz, boolean keepFirstWhenConflict) {
        AbstractNodeMerger<T> merger = null;
        Class<?> type = clazz;
        while (merger == null && type != null) {
            merger = map.get(type);
            type = type.getSuperclass();
        }
        logger.debug("get merger for class => {} {}", clazz.getName(), merger != null);
        merger.setKeepFirstWhenConflict(keepFirstWhenConflict);
        return merger;
    }

    public N doMerge(Optional<N> optional1, Optional<N> optional2) {

        if (optional1.isPresent() && optional2.isPresent()) {
            AbstractNodeMerger merger = AbstractNodeMerger.getMerger(optional1.get().getClass(), isKeepFirstWhenConflict());
            return (N) merger.doMerge(optional1.get(), optional2.get());
        }

        if (optional1.isPresent() && !optional2.isPresent()) {
            return optional1.get();
        }

        if (optional2.isPresent() && !optional1.isPresent()) {
            return optional2.get();
        }

        return null;
    }

    public boolean isEqual(NodeList<N> first, NodeList<N> second) {
        if (first.isEmpty() && second.isEmpty()) {
            return true;
        }

        if (first.isEmpty() && !second.isEmpty()) {
            return false;
        }

        if (!first.isEmpty() && second.isEmpty()) {
            return false;
        }
        NodeList<N> nodeList = new NodeList<>();

        NodeList<N> secondCopies = new NodeList<>(second);
        // todo
        N found = null;
        for (N firstNode : first) {
            AbstractNodeMerger merger = AbstractNodeMerger.getMerger(firstNode.getClass(), isKeepFirstWhenConflict());
            for (N secondNode : secondCopies) {
                if (firstNode.getClass().equals(secondNode.getClass())
                        && merger.isEqual(firstNode, secondNode)) {
                    found = secondNode;
                    break;
                }
            }

            if (null != found) {
                N node = (N) merger.doMerge(firstNode, found);
                nodeList.add(node);
                secondCopies.remove(found);
            } else {
                nodeList.add(firstNode);
            }
        }


        if (secondCopies.isNonEmpty()) {
            nodeList.addAll(secondCopies);
        }

        return true;
    }

    /**
     * 节点是否相同
     *
     * @param first  第一个
     * @param second 第二个
     * @return boolean
     */
    public abstract boolean isEqual(N first, N second);

    /**
     * 执行节点合并
     *
     * @param first  第一个
     * @param second 第二个
     * @return {@link Optional}
     */
    public N doMerge(N first, N second) {
        return isKeepFirstWhenConflict() ? first : second;
    }

    public boolean isEqual(Optional<N> optional1, Optional<N> optional2) {
        if (optional1.isPresent() && optional2.isPresent()) {
            AbstractNodeMerger merger = AbstractNodeMerger.getMerger(optional1.get().getClass(), isKeepFirstWhenConflict());
            return merger.isEqual(optional1.get(), optional2.get());
        }

        return optional1.isPresent() == optional2.isPresent();
    }

    public boolean isKeepFirstWhenConflict() {
        return keepFirstWhenConflict;
    }

    public void setKeepFirstWhenConflict(boolean keepFirstWhenConflict) {
        this.keepFirstWhenConflict = keepFirstWhenConflict;
    }

    /**
     * 合并
     *
     * @param first  第一个
     * @param second 第二个
     * @return {@link Optional}
     */
    public Optional<N> merge(N first, N second) {
        return merge(Optional.of(first), Optional.of(second));
    }

    /**
     * 合并
     *
     * @param first  第一个
     * @param second 第二个
     * @return {@link Optional}
     */
    public Optional<N> merge(Optional<N> first, Optional<N> second) {
        if (!first.isPresent()) {
            return second;
        }

        if (!second.isPresent()) {
            return first;
        }
        AbstractNodeMerger merger = AbstractNodeMerger.getMerger(first.get().getClass(), isKeepFirstWhenConflict());
        if (merger.isEqual(first.get(), second.get())) {
            return first;
        }

        return Optional.of((N) merger.doMerge(first.get(), second.get()));
    }

    public Optional<NodeList<N>> mergeCollection(Optional<NodeList<N>> first, Optional<NodeList<N>> second) {
        if (!first.isPresent()) {
            return second;
        }

        return second.map(ns -> Optional.of(mergeCollection(first.get(), ns))).orElse(first);
    }

    public NodeList<N> mergeCollection(NodeList<N> first, NodeList<N> second) {
        NodeList<N> nodeList = new NodeList<>();
        if (first.isEmpty()) {
            return second;
        }

        if (second.isEmpty()) {
            return first;
        }

        NodeList<N> secondCopies = new NodeList<>(second);
        N found = null;
        for (N firstNode : first) {
            AbstractNodeMerger merger = AbstractNodeMerger.getMerger(firstNode.getClass(), isKeepFirstWhenConflict());
            for (N secondNode : secondCopies) {
                if (merger.isEqual(firstNode, secondNode)) {
                    found = secondNode;
                    break;
                }
            }

            if (null != found) {
                N mergeResult = (N) merger.doMerge(firstNode, found);
                nodeList.add(mergeResult);
                secondCopies.remove(found);
            } else {
                nodeList.add(firstNode);
            }
        }


        if (secondCopies.isNonEmpty()) {
            nodeList.addAll(secondCopies);
        }

        return nodeList;
    }
}
