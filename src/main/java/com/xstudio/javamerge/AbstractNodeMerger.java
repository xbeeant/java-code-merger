package com.xstudio.javamerge;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.type.VoidType;
import com.xstudio.javamerge.merger.ast.ImportDeclarationMerger;
import com.xstudio.javamerge.merger.ast.ModifierMerger;
import com.xstudio.javamerge.merger.ast.PackageDeclarationMerger;
import com.xstudio.javamerge.merger.ast.expr.*;
import com.xstudio.javamerge.merger.ast.stmt.BlockStmtMerger;
import com.xstudio.javamerge.merger.ast.stmt.ExpressionStmtMerger;
import com.xstudio.javamerge.merger.ast.stmt.StatementMerger;
import com.xstudio.javamerge.merger.ast.type.ClassOrInterfaceTypeMerger;
import com.xstudio.javamerge.merger.ast.type.TypeMerger;
import com.xstudio.javamerge.merger.ast.type.TypeParameterMerger;
import com.xstudio.javamerge.merger.ast.type.VoidTypeMerger;
import com.xstudio.javamerge.merger.body.FieldDeclarationMerger;
import com.xstudio.javamerge.merger.body.MethodDeclarationMerger;
import com.xstudio.javamerge.merger.comments.CommentMerger;
import com.xstudio.javamerge.merger.comments.JavadocCommentMerger;
import com.xstudio.javamerge.merger.comments.LineCommentMerger;
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
        map.put(VoidType.class, new VoidTypeMerger());
        map.put(TypeParameter.class, new TypeParameterMerger());

        // ast.expr
        map.put(AnnotationExpr.class, new AnnotationExprMerger());
        map.put(MemberValuePair.class, new MemberValuePairMerger());
        map.put(NormalAnnotationExpr.class, new NormalAnnotationExprMerger());
        map.put(SimpleName.class, new SimpleNameMerger());
        map.put(Name.class, new NameMerger());
        map.put(Expression.class, new ExpressionMerger());
        map.put(VariableDeclarationExpr.class, new VariableDeclarationExprMerger());
        map.put(ReturnStmt.class, new ReturnStmtMerger());

        // ast.body
        map.put(FieldDeclaration.class, new FieldDeclarationMerger());
        map.put(MethodDeclaration.class, new MethodDeclarationMerger());
        // ast.stmt
        map.put(BlockStmt.class, new BlockStmtMerger());
        map.put(Statement.class, new StatementMerger());
        map.put(ExpressionStmt.class, new ExpressionStmtMerger());
        // comments
        map.put(Comment.class, new CommentMerger());
        map.put(LineComment.class, new LineCommentMerger());
        map.put(JavadocComment.class, new JavadocCommentMerger());
    }

    private boolean keepFirstWhenConflict;

    /**
     * 获得合并器
     *
     * @param clazz                 clazz
     * @param keepFirstWhenConflict
     * @return {@link AbstractNodeMerger <T>}
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
            for (N secondNode : secondCopies) {
                if (isEqual(firstNode, secondNode)) {
                    found = secondNode;
                    break;
                }
            }

            if (null != found) {
                Optional<N> mergeResult = Optional.of(doMerge(firstNode, found));
                nodeList.add(mergeResult.get());
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
     * @return {@link Optional<N>}
     */
    public N doMerge(N first, N second) {
        return isKeepFirstWhenConflict() ? first : second;
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
     * @return {@link Optional<N>}
     */
    public Optional<N> merge(N first, N second) {
        return merge(Optional.of(first), Optional.of(second));
    }

    /**
     * 合并
     *
     * @param first  第一个
     * @param second 第二个
     * @return {@link Optional<N>}
     */
    public Optional<N> merge(Optional<N> first, Optional<N> second) {
        if (!first.isPresent()) {
            return second;
        }

        if (!second.isPresent()) {
            return first;
        }

        if (isEqual(first.get(), second.get())) {
            return first;
        }

        return Optional.of(doMerge(first.get(), second.get()));
    }

    public Optional<NodeList<N>> mergeCollection(Optional<NodeList<N>> first, Optional<NodeList<N>> second) {
        if (!first.isPresent()) {
            return second;
        }

        if (!second.isPresent()) {
            return first;
        }

        return Optional.of(mergeCollection(first.get(), second.get()));
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
            for (N secondNode : secondCopies) {
                if (isEqual(firstNode, secondNode)) {
                    found = secondNode;
                    break;
                }
            }

            if (null != found) {
                Optional<N> mergeResult = Optional.of(doMerge(firstNode, found));
                nodeList.add(mergeResult.get());
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