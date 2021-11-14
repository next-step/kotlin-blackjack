package global.strategy.ui.output

object ConsoleOutputStrategy : OutputStrategy {
    override fun execute(message: String) {
        println(message)
    }
}