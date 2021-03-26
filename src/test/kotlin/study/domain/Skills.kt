package study.domain

class Skills(private val skills: List<Skill>) {
    fun toList(): List<Skill> {
        return this.skills
    }
}
