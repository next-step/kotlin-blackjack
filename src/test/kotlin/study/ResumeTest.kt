package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import study.domain.Resume
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

        assertThat(resume.name).isEqualTo("남동민")
    }

    @Test
    internal fun `회사 작성`() {
        val resume: Resume = introduce {
            name("남동민")
            company("에이치티비욘드")
        }

        assertThat(resume.company).isEqualTo("에이치티비욘드")
    }
}
