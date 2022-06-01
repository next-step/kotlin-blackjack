package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import study.resume.introduce

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
//            skills {
//                soft("kotlin")
//                hard("playing")
//            }
        }
        assertThat(introduce.name).isEqualTo("pang")
        assertThat(introduce.company).isEqualTo("kakao")

    }
}