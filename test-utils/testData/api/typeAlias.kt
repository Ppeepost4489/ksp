/*
 * Copyright 2020 Google LLC
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// WITH_RUNTIME
// TEST PROCESSOR: TypeAliasProcessor
// EXPECTED:
// a : A = String
// b : B = String
// c : CC = A = String
// d : String
// listOfInt : ListOfInt = List<Int>
// listOfInt_B : ListOfInt_B = ListOfInt = List<Int>
// listOfInt_C : ListOfInt_C = ListOfInt_B = ListOfInt = List<Int>
// myList : MyList<Long> = List<T>
// myList_B : List<Number>
// myList_String : MyList_String = MyList<String> = List<T>
// myList_b_String : MyList_B_String = MyList_B<String> = MyList<R> = List<T>
// myListOfAlias : MyListOfAlias = List<A>
// myListOfAliasInLib : MyListOfAliasInLib = List<@JvmSuppressWildcards AInLib>
// viewBinderProviders : Map<Class<BaseViewHolder>, @JvmSuppressWildcards Provider<BaseEmbedViewBinder>>
// nested1 : MyList<ListOfInt> = List<T>
// nested2 : List<ListOfInt>
// END

// MODULE: module1
// FILE: KotlinLib.kt
typealias AInLib = String
typealias MyListOfAliasInLib = List<@JvmSuppressWildcards AInLib>

// MODULE: main(module1)
// FILE: KotlinSrc.kt
typealias A = String
typealias B = String
typealias CC = A
typealias ListOfInt = List<Int>
typealias ListOfInt_B = ListOfInt
typealias ListOfInt_C = ListOfInt_B
typealias MyList<T> = List<T>
typealias MyList_B<R> = MyList<R>
typealias MyList_String = MyList<String>
typealias MyList_B_String = MyList_B<String>
typealias MyListOfAlias = List<@JvmSuppressWildcards A>

val a: A = ""
val b: B = ""
val c: CC = ""
val d: String = ""
val listOfInt: ListOfInt = TODO()
val listOfInt_B: ListOfInt_B = TODO()
val listOfInt_C: ListOfInt_C = TODO()
val myList: MyList<Long> = TODO()
val myList_B: List<Number> = TODO()
val myList_String: MyList_String = TODO()
val myList_b_String: MyList_B_String = TODO()
// FIXME: type annotation is missing
val myListOfAlias: MyListOfAlias = TODO()
val myListOfAliasInLib: MyListOfAliasInLib = TODO()

interface BaseViewHolder
interface SpaceshipEmbedModel
interface Provider<T>
interface ViewBinder<T1, T2>
typealias BaseEmbedViewBinder = ViewBinder<out BaseViewHolder, out SpaceshipEmbedModel>

val viewBinderProviders: Map<Class<out BaseViewHolder>, @JvmSuppressWildcards Provider<BaseEmbedViewBinder>> = TODO()
val nested1: MyList<ListOfInt>
val nested2: List<ListOfInt>
