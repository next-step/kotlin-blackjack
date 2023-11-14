package dto

import domain.Card

data class GameResultDTO(
    val finalScores: Map<String, Pair<List<Card>, String>>
)
