package blackjack.service

class BlackJackService {
    fun splitInput(input: String): List<String> {
        return input.split(",")
    }
}