package blackjack.domain.player

class PlayerNames(elements: List<String>) : Iterable<String> {
    private val _elements: Set<String>

    init {
        validateDuplitatedName(elements)
        _elements = elements.toSet()
    }

    private fun validateDuplitatedName(elements: List<String>) {
        require(elements.size == elements.distinct().size)
    }

    override fun iterator(): Iterator<String> {
        return _elements.iterator()
    }
}
