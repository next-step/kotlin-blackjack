package study.resume

import study.resume.language.LanguageBuilder
import study.resume.language.LanguageSkill
import study.resume.skill.Skill
import study.resume.skill.SkillBuilder

data class Resume(
    val name: String,
    val company: String?,
    val skills: List<Skill>,
    val language: List<LanguageSkill>
)

fun introduce(block: ResumeBuilder.() -> Unit): Resume {
    return ResumeBuilder().apply(block).build()
}

class ResumeBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: List<Skill> = emptyList()
    private var language: List<LanguageSkill> = emptyList()

    fun build(): Resume {
        return Resume(
            name,
            company,
            skills,
            language
        )
    }

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        language = LanguageBuilder().apply(block).build()
    }
}
