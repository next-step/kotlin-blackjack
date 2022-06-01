package study.resume

import study.resume.skill.Skill
import study.resume.skill.SkillBuilder

data class Resume(
    val name: String,
    val company: String,
    val skills: List<Skill>,
//    val language: List<String>
)

fun introduce(block: ResumeBuilder.() -> Unit): Resume {
    return ResumeBuilder().apply(block).build()
}

class ResumeBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: List<Skill>

    fun build(): Resume {
        return Resume(
            name,
            company,
            skills
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
}
