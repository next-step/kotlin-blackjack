package introduce.domain

class Skills {
    val soft: MutableList<String> = mutableListOf()
    val hard: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }
}
