package blackjack.domain

sealed class Decision
object HitDecision: Decision()
object StayDecision: Decision()
