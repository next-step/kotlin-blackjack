import assemble.ObjectAssemble
import common.Executable

class BlackJackApp(
    private val executable: Executable
) : Executable by executable

fun main() {
    val executable = ObjectAssemble.executableApp()
    val blackJackApplication = BlackJackApp(executable)
    blackJackApplication.execute()
}
