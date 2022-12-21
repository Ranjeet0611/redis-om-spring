package com.redis.om.spring.metamodel.nonindexed;

import java.util.function.Consumer;
import java.util.function.ToLongFunction;

import com.redis.om.spring.metamodel.MetamodelField;
import com.redis.om.spring.metamodel.SearchFieldAccessor;
import com.redis.om.spring.search.stream.actions.ArrayAppendAction;
import com.redis.om.spring.search.stream.actions.ArrayIndexOfAction;
import com.redis.om.spring.search.stream.actions.ArrayInsertAction;
import com.redis.om.spring.search.stream.actions.ArrayLengthAction;
import com.redis.om.spring.search.stream.actions.ArrayPopAction;
import com.redis.om.spring.search.stream.actions.ArrayTrimAction;

public class NonIndexedTagField<E, T> extends MetamodelField<E, T> {

  public NonIndexedTagField(SearchFieldAccessor field, boolean indexed) {
    super(field, indexed);
  }

  public Consumer<? super E> add(Object value) {
    return new ArrayAppendAction<>(searchFieldAccessor, value);
  }

  public Consumer<? super E> insert(Object value, Long index) {
    return new ArrayInsertAction<>(searchFieldAccessor, value, index);
  }

  public Consumer<? super E> prepend(Object value) {
    return new ArrayInsertAction<>(searchFieldAccessor, value, 0L);
  }

  public ToLongFunction<? super E> length() {
    return new ArrayLengthAction<>(searchFieldAccessor);
  }

  public ToLongFunction<? super E> indexOf(Object element) {
    return new ArrayIndexOfAction<>(searchFieldAccessor, element);
  }

  public <R> ArrayPopAction<? super E,R> pop(Long index) {
    return new ArrayPopAction<>(searchFieldAccessor, index);
  }

  public <R> ArrayPopAction<? super E,R> pop() {
    return pop(-1L);
  }

  public <R> ArrayPopAction<? super E,R> removeFirst() {
    return pop(0L);
  }

  public <R> ArrayPopAction<? super E,R> removeLast() {
    return pop(-1L);
  }

  public <R> ArrayPopAction<? super E,R> remove(Long index) {
    return pop(index);
  }

  public Consumer<? super E> trimToRange(Long begin, Long end) {
    return new ArrayTrimAction<>(searchFieldAccessor, begin, end);
  }

}
