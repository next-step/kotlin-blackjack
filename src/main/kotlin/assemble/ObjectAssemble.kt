package assemble

import common.Executable
import interfaces.BlackJackController

object ObjectAssemble {
    fun executableApp(): Executable = BlackJackController()
}
