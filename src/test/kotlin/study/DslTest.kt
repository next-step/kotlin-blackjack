package study

import kotlindsl.Resume
import kotlindsl.introduce
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*
introduce {
    name("나윤지")
    company("마켓")
    skills {
        soft("A passion for problem solving")
        soft("Good communication skills")
        hard("Kotlin")
    }
    languages {
        "Korean" level 5
        "English" level 3
    }
}
*/
class DslTest {
    @ValueSource(strings = ["나윤지", "yjna2316"])
    @ParameterizedTest
    fun introduceName(value: String) {
        val resume: Resume = introduce {
            name(value)
        }
        assertThat(resume.name).isEqualTo(value)
    }

    @Test
    fun introduceCompany() {
        val resume: Resume = introduce {
            name("나윤지")
            company("마켓")
        }
        assertThat(resume.name).isEqualTo("나윤지")
        assertThat(resume.company).isEqualTo("마켓")
    }

    @Test
    fun introduceSoftSkill() {
        val resume: Resume = introduce {
            name("나윤지")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
            }
        }
        assertThat(resume.name).isEqualTo("나윤지")
        assertNotNull(resume.skills)
        assertThat(resume.skills!!.soft.softSkills.size).isEqualTo(2)
        assertEquals(
            resume.skills!!.soft.softSkills,
            listOf("A passion for problem solving", "Good communication skills")
        )
    }

    @Test
    fun introduceSoftHardSkills() {
        val resume: Resume = introduce {
            name("나윤지")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(resume.name).isEqualTo("나윤지")
        assertNotNull(resume.skills)
        assertThat(resume.skills!!.soft.softSkills.size).isEqualTo(2)
        assertThat(resume.skills!!.hard.hardSkills.size).isEqualTo(1)
        assertEquals(
            resume.skills!!.soft.softSkills,
            listOf("A passion for problem solving", "Good communication skills")
        )
        assertEquals(resume.skills!!.hard.hardSkills, listOf("Kotlin"))
    }

    @Test
    fun introduceLanguages() {
        val resume: Resume = introduce {
            name("나윤지")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(resume.name).isEqualTo("나윤지")
        assertNotNull(resume.languages)
        assertThat(resume.languages!!.levelMap.size).isEqualTo(2)
        assertEquals(
            resume.languages!!.levelMap,
            mapOf("Korean" to 5, "English" to 3)
        )
    }
}
