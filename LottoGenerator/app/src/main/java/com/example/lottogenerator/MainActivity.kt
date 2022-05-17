package com.example.lottogenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lottogenerator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.generatebutton.setOnClickListener {
            val lottoNumbers = createLottoNumbers()
            Log.d("TAG", lottoNumbers.toString())
        }
    }

    private fun createLottoNumbers(): ArrayList<Int> {

        val result = arrayListOf<Int>()

        val source = IntArray(45){
            it + 1
        }
        val seed = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.KOREA).format(Date()).hashCode().toLong()
        val random = Random(seed)//랜덤 숫자 생성 (java.util 아님)
        source.shuffle(random)
        source.slice(0..5).forEach{ num -> result.add(num)} // 앞에서 6개 뽑아냄 forEach를 통해 값을 arrayList에 저장
        result.sort() // 숫자를 순서대로 정렬

        var evenNumberCount = 0
        var oddNumberCount = 0
        for (num in result) {
            if (num % 2 == 0 ) {
                evenNumberCount += 1
            } else {
                oddNumberCount += 1
            }
        }
        result.add(result.sum()) // 배열의 전체 합 구함
        result.add(oddNumberCount) // 홀수 수
        result.add(evenNumberCount)  // 짝수 수

        // arrayList == 로또 숫자 6개와 배열의 합, 홀수의 개수, 짝수의 개수를 가지고 있는 배열
        return result

    }


}