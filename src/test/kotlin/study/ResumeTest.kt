package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import study.resume.introduce
import study.resume.language.LanguageSkill
import study.resume.skill.HardSkill
import study.resume.skill.SoftSkill

/*
introduce {
    name("박재성")
    company("우아한형제들")
    skills {
        soft("A passion for problem solving")
        soft("Good communication skills")
        hard("Kotlin")
    }
    languages {
        "Korean" level 5
        "English" level 3
    }
}*/
class ResumeTest {

    @Test
    fun resume() {
        val introduce = introduce {
            name("pang")
            company("kakao")
            skills {
                soft("kotlin")
                hard("playing")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(introduce.name).isEqualTo("pang")
        assertThat(introduce.company).isEqualTo("kakao")
        assertThat(introduce.skills).contains(SoftSkill("kotlin"))
        assertThat(introduce.skills).contains(HardSkill("playing"))
        assertThat(introduce.language).contains(LanguageSkill("Korean", 5))
        assertThat(introduce.language).contains(LanguageSkill("English", 3))
    }

    @Test
    fun `company는 null을 허용한다`() {
        val resume = introduce {
            name("pang")
            skills {
                soft("kotlin")
                hard("playing")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(resume.company).isNull()
    }
}
