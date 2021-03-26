package study.domain

class Skills(val skills: List<Skill>) {
    fun toList(): List<Skill> {
        return this.skills
    }
}
