package study.domain

fun introduce(init: IntroduceDsl.() -> Unit): Resume {
    return IntroduceDsl()
        .apply(init)
        .build()
}

class IntroduceDsl {
    lateinit var name: String

    var company: String? = null

    val skills: MutableList<Skill> = mutableListOf()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(init: SkillsDsl.() -> Unit) {
        this.skills.addAll(SkillsDsl().apply(init).build())
    }

    fun build(): Resume {
        return Resume(
            name = Name(name),
            company = company?.let { Company(it) },
            skills = Skills(skills)
        )
    }

    class SkillsDsl {
        val skills: MutableList<Skill> = mutableListOf()
        fun soft(skill: String) {
            skills.add(SoftSkill(skill))
        }

        fun hard(skill: String) {
            skills.add(HardSkill(skill))
        }

        fun build(): List<Skill> {
            return skills
        }
    }
}
