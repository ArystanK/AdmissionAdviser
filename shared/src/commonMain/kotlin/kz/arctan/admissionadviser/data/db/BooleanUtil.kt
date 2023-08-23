package kz.arctan.admissionadviser.data.db

fun Boolean.toLong(): Long = if (this) 1L else 0L