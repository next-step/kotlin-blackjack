package study.domain

fun introduce(init: IntroduceDsl.() -> Unit): Resume {
    return IntroduceDsl()
        .apply(init)
        .build()
}

class IntroduceDsl {
    private lateinit var name: String

    private var company: String? = null

    private val skills: MutableList<Skill> = mutableListOf()

    private val languages: MutableList<Language> = mutableListOf()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(init: SkillsDsl.() -> Unit) {
        this.skills.addAll(SkillsDsl().apply(init).build())
    }

    fun languages(init: LanguagesDsl.() -> Unit) {
        this.languages.addAll(LanguagesDsl().apply(init).build())
    }

    fun build(): Resume {
        return Resume(
            name = Name(name),
            company = company?.let { Company(it) },
            skills = Skills(skills),
            languages = Languages(languages)
        )
    }

    class SkillsDsl {
        private val skills: MutableList<Skill> = mutableListOf()
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

    class LanguagesDsl {
        private val languages: MutableList<Language> = mutableListOf()

        infix fun String.level(level: Int) {
            languages.add(Language(language = this, level = level))
        }

        fun build(): List<Language> {
            return this.languages
        }
    }
}
