package study

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

/*
introduce {
  name("권재용")
  company("KSD")
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
class DslTest : AnnotationSpec() {

    @Test
    fun introduce() {
        val person: Person = introduce {
            name("권재용")
        }

        person.name shouldBe "권재용"
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("권재용")
            company("KSD")
        }

        person.name shouldBe "권재용"
        person.company shouldBe "KSD"
    }

    @Test
    fun skills() {
        val person: Person = introduce {
            name("권재용")
            company("KSD")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        person.name shouldBe "권재용"
        person.company shouldBe "KSD"
        assertSoftly(person.skills!!) {
            soft shouldBe hashSetOf("A passion for problem solving", "Good communication skills")
            hard shouldBe hashSetOf("Kotlin")
        }
    }

    @Test
    fun Languages() {
        val person: Person = introduce {
            name("권재용")
            company("KSD")
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

        person.name shouldBe "권재용"
        person.company shouldBe "KSD"
        assertSoftly(person.skills!!) {
            soft shouldBe hashSetOf("A passion for problem solving", "Good communication skills")
            hard shouldBe hashSetOf("Kotlin")
        }
        assertSoftly(person.languages!!) {
            languages[Language("Korean")] shouldBe Grade(5)
            languages[Language("English")] shouldBe Grade(3)
        }
    }
}
