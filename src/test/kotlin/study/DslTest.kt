package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import study.Person.Companion.introduce

class DslTest : StringSpec({

    "introduce" {
        val person: Person = introduce {
            name("손재빈")
        }

        person.name shouldBe "손재빈"
    }

    "company" {
        val person: Person = introduce {
            name("손재빈")
            company("인프랩")
        }

        person.company shouldBe "인프랩"
    }
})
