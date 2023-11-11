package dsl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DslTest : StringSpec({

    "name Test" {
        listOf("박재성", "제이슨").forEach { value ->
            val person = introduce {
                name(value)
            }

            person.name shouldBe value
        }
    }
})
