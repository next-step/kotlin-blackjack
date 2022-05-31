package kotlindsl.util

abstract class AddOnlyMutableList<E>(private val mutableList: MutableList<E> = mutableListOf()) :
    List<E> by mutableList {
    fun add(element: E) = this.mutableList.add(element)
}
