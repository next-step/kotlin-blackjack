package study

class Skills {
    private val elements: MutableList<Skill> = mutableListOf()

    fun hard(name: String) {
        elements.add(Hard(name))
    }

    fun soft(name: String) {
        elements.add(Soft(name))
    }

    fun toList() = elements
}
