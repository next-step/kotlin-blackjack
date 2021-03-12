package study

class Skills {
    var values: MutableList<Skill> = mutableListOf()

    fun hard(name: String) {
        values.add(Hard(name))
    }

    fun soft(name: String) {
        values.add(Soft(name))
    }
}

sealed class Skill

data class Hard(val name: String) : Skill()
data class Soft(val name: String) : Skill()
