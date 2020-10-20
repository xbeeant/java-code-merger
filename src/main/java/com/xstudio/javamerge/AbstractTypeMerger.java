package com.xstudio.javamerge;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.xstudio.javamerge.merger.body.ClassOrInterfaceDeclarationMerger;
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
public abstract class AbstractTypeMerger<N extends TypeDeclaration<?>> {
    public static final Logger logger = LoggerFactory.getLogger(AbstractTypeMerger.class);

    private static final ConcurrentHashMap<Class, AbstractTypeMerger> map = new ConcurrentHashMap<>();

    private boolean keepFirstWhenConflict;

    static {
        map.put(ClassOrInterfaceDeclaration.class, new ClassOrInterfaceDeclarationMerger());
    }

    public boolean isKeepFirstWhenConflict() {
        return keepFirstWhenConflict;
    }

    public void setKeepFirstWhenConflict(boolean keepFirstWhenConflict) {
        this.keepFirstWhenConflict = keepFirstWhenConflict;
    }

    /**
     * 获得合并器
     *
     * @param clazz clazz
     * @return {@link AbstractTypeMerger <T>}
     */
    public static <T extends TypeDeclaration<?>> AbstractTypeMerger<T> getMerger(Class<T> clazz, boolean keepFirstWhenConflict) {
        AbstractTypeMerger<T> merger = null;

        Class<?> type = clazz;
        while (merger == null && type != null) {
            merger = map.get(type);
            type = type.getSuperclass();
        }
        logger.debug("get merger for class => {} {}", clazz.getName(), merger != null);
        merger.setKeepFirstWhenConflict(keepFirstWhenConflict);
        return merger;
    }

    /**
     * 合并
     *
     * @param first  第一个
     * @param second 第二个
     * @return {@link Optional<N>}
     */
    public N merge(N first, N second) {
        if (first == null) {
            return second;
        }

        if (second == null) {
            return first;
        }

        if (isEqual(first, second)) {
            return first;
        }

        return doMerge(first, second);
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
    public abstract N doMerge(N first, N second);

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
                N mergeResult = doMerge(firstNode, found);
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
