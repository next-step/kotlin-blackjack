package kotlinDSL

class Skills {
    private val skills: MutableList<Skill> = mutableListOf()

    fun hard(name: String) {
        skills.add(Hard(name))
    }

    fun soft(name: String) {
        skills.add(Soft(name))
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is Skills){
            return false
        }
        return skills == other.skills
    }
}