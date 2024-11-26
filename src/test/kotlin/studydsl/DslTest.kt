package studydsl

import io.kotest.matchers.shouldBe
import org.testng.annotations.Test

/*
introduce {
    name("김창민")
    company("가비아")
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
    @Test
    fun name() {
        val person = introduce {
            name("김창민")
        }
        person.name shouldBe "김창민"
    }

    @Test
    fun company() {
        val person = introduce {
            name("김창민")
            company("가비아")
        }
        person.company shouldBe "가비아"
    }

    fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply { block }.build()
    }
}
