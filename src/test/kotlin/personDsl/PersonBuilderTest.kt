package personDsl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PersonBuilderTest {
    @ParameterizedTest
    @ValueSource(strings = ["Karyna", "Name"])
    fun `when name is valid should assign name`(name: String) {
        val person: Person =
            introduce {
                name(name)
            }
        person.name shouldBe name
    }

    @ParameterizedTest
    @ValueSource(strings = ["DH"])
    fun `when company is valid should assign company`(company: String) {
        val person: Person =
            introduce {
                name("Karyna")
                company(company)
            }
        person.company shouldBe company
    }
}
