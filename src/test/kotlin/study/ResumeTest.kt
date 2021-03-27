package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import study.domain.Company
import study.domain.HardSkill
import study.domain.Language
import study.domain.Name
import study.domain.Resume
import study.domain.SoftSkill
import study.domain.introduce

/*
introduce {
  name("남동민")
  company("에이치티비욘드")
  skills {
    soft ("A passion for problem solving")
    soft ("Attitude to develop with a spirit of improvement")
    hard ("Kotlin")
  }
  languages {
    "Korean" level 5
    "English" level 3
  }
}
 */
internal class ResumeTest {
    @Test
    internal fun `이름 작성`() {
        val resume: Resume = introduce {
            name("남동민")
        }

        assertThat(resume.name).isEqualTo(Name("남동민"))
    }

    @Test
    internal fun `회사 작성`() {
        val resume: Resume = introduce {
            name("남동민")
            company("에이치티비욘드")
        }

        assertThat(resume.company).isEqualTo(Company("에이치티비욘드"))
    }

    @Test
    internal fun `스킬 목록 작성`() {
        val resume: Resume = introduce {
            name("남동민")
            company("에이치티비욘드")
            skills {
                soft("A passion for problem solving")
                soft("Attitude to develop with a spirit of improvement")
                hard("Kotlin")
            }
        }

        assertThat(resume.skills.toList()).containsExactlyInAnyOrder(
            SoftSkill("A passion for problem solving"),
            SoftSkill("Attitude to develop with a spirit of improvement"),
            HardSkill("Kotlin")
        )
    }

    @Test
    internal fun `언어 목록 작성`() {
        val resume: Resume = introduce {
            name("남동민")
            company("에이치티비욘드")
            skills {
                soft("A passion for problem solving")
                soft("Attitude to develop with a spirit of improvement")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(resume.languages.toList()).containsExactlyInAnyOrder(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}
