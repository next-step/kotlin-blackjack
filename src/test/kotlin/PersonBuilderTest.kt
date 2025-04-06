import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PersonBuilderTest {
    @ParameterizedTest
    @ValueSource(strings = ["Aparna", "Pattathil"])
    fun `test name assignment`(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }
    @ParameterizedTest
    @ValueSource(strings = ["DH", ""])
    fun `test company assignment`(value: String) {
        val person = introduce {
            name("Aparna")
            company(value)
        }
        assertThat(person.company).isEqualTo(value)
    }

}