package study

class Skills {
    val soft = mutableListOf<String>()
    val hard = mutableListOf<String>()

    fun soft(skill: String) {
        this.soft.add(skill)
    }

    fun hard(skill: String) {
        this.hard.add(skill)
    }
}
