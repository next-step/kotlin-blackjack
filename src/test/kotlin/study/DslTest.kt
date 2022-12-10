package study

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import study.Skill.Hard
import study.Skill.Soft

/*introduce {
    name("이민재")
    company("무직")
    skills {
        soft("Being lazy")
        soft("Worst communication skills")
        hard("Destroy")
    }
    languages {
        "Korean" level 2
        "English" level 1
    }
}*/
internal class DslTest {
    @Test
    internal fun introduce() {
        val person: Person = introduce {
            name("이민재")
        }
        person.name shouldBe "이민재"
        person.company.shouldBeNull()
    }

    @Test
    internal fun company() {
        val person: Person = introduce {
            name("이민재")
            company("무직")
        }
        person.name shouldBe "이민재"
        person.company shouldBe "무직"
    }

    @Test
    internal fun skills() {
        val person: Person = introduce {
            name("이민재")
            company("무직")
            skills {
                soft("Being lazy")
                soft("Worst communication skills")
                hard("Destroy")
            }
        }

        person.name shouldBe "이민재"
        person.company shouldBe "무직"
        person.skills?.value shouldBe listOf(
            Soft("Being lazy"),
            Soft("Worst communication skills"),
            Hard("Destroy")
        )
    }

    @Test
    internal fun languages() {
        val person: Person = introduce {
            name("이민재")
            company("무직")
            skills {
                soft("Being lazy")
                soft("Worst communication skills")
                hard("Destroy")
            }
            languages {
                "Korean" level 2
                "English" level 1
            }
        }

        person.name shouldBe "이민재"
        person.company shouldBe "무직"
        person.skills?.value shouldContainExactlyInAnyOrder listOf(
            Soft("Being lazy"),
            Soft("Worst communication skills"),
            Hard("Destroy")
        )
        person.languages?.value shouldContainExactlyInAnyOrder listOf(
            Language("Korean", 2),
            Language("English", 1)
        )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()


