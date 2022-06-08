package blackjack.view.output

import blackjack.view.output.converter.OutputConverter

object OutputView {
    fun <T> println(printable: T, outputConverter: OutputConverter<T>) {
        print(printable, outputConverter)
        println()
    }

    fun <T> print(printable: T, outputConverter: OutputConverter<T>) {
        println(outputConverter.convert(printable))
    }
}
