package com.zyh.pro.xmlparser.main;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface ITree<Self extends ITree<Self>> { // FIXME 2020/4/19  wait for me!!!  to abstractClass if must

	Self getChildAt(int at);

	List<Self> getChildren();

	void addChild(Self child);

	Self self();

	default <T extends ITree<T>> T convert(Function<Self, T> converter) {
		T root = converter.apply(self());
		for (Self child : getChildren())
			root.addChild(child.convert(converter));
		return root;
	}

	boolean hasChild();

	default <Leaf, Composite extends Leaf> Leaf convertWithLeaf(Function<? super Self, ? extends Composite> toGroup,
	                                                            Function<? super Self, ? extends Leaf> toChild, BiConsumer<? super Composite, ? super Leaf> addChild) {
		if (!hasChild())
			return toChild.apply(self());

		Composite root = toGroup.apply(self());
		for (Self child : getChildren())
			addChild.accept(root, child.convertWithLeaf(toGroup, toChild, addChild));
		return root;
	}
}
