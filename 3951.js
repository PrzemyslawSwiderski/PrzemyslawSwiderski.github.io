"use strict";(this.webpackChunkpswidersk_page=this.webpackChunkpswidersk_page||[]).push([[3951],{3951:t=>{t.exports="(function (root, factory) {\n  if (typeof define === 'function' && define.amd)\n    define(['exports', './kotlin-kotlin-stdlib.js'], factory);\n  else if (typeof exports === 'object')\n    factory(module.exports, require('./kotlin-kotlin-stdlib.js'));\n  else {\n    if (typeof this['kotlin-kotlin-stdlib'] === 'undefined') {\n      throw new Error(\"Error loading module 'kotlinx-atomicfu'. Its dependency 'kotlin-kotlin-stdlib' was not found. Please, check whether 'kotlin-kotlin-stdlib' is loaded prior to 'kotlinx-atomicfu'.\");\n    }\n    root['kotlinx-atomicfu'] = factory(typeof this['kotlinx-atomicfu'] === 'undefined' ? {} : this['kotlinx-atomicfu'], this['kotlin-kotlin-stdlib']);\n  }\n}(globalThis, function (_, kotlin_kotlin) {\n  'use strict';\n  //region block: imports\n  var protoOf = kotlin_kotlin.$_$.h8;\n  var initMetadataForObject = kotlin_kotlin.$_$.o7;\n  var VOID = kotlin_kotlin.$_$.f;\n  var initMetadataForClass = kotlin_kotlin.$_$.j7;\n  var toString = kotlin_kotlin.$_$.kb;\n  //endregion\n  //region block: pre-declaration\n  initMetadataForClass(atomicfu$TraceBase, 'TraceBase');\n  initMetadataForObject(None, 'None', VOID, atomicfu$TraceBase);\n  initMetadataForClass(AtomicRef, 'AtomicRef');\n  initMetadataForClass(AtomicBoolean, 'AtomicBoolean');\n  initMetadataForClass(AtomicInt, 'AtomicInt');\n  //endregion\n  function None() {\n    None_instance = this;\n    atomicfu$TraceBase.call(this);\n  }\n  var None_instance;\n  function None_getInstance() {\n    if (None_instance == null)\n      new None();\n    return None_instance;\n  }\n  function atomicfu$TraceBase() {\n  }\n  protoOf(atomicfu$TraceBase).atomicfu$Trace$append$1 = function (event) {\n  };\n  protoOf(atomicfu$TraceBase).atomicfu$Trace$append$2 = function (event1, event2) {\n  };\n  protoOf(atomicfu$TraceBase).atomicfu$Trace$append$3 = function (event1, event2, event3) {\n  };\n  protoOf(atomicfu$TraceBase).atomicfu$Trace$append$4 = function (event1, event2, event3, event4) {\n  };\n  function AtomicRef(value) {\n    this.kotlinx$atomicfu$value = value;\n  }\n  protoOf(AtomicRef).g1i = function (_set____db54di) {\n    this.kotlinx$atomicfu$value = _set____db54di;\n  };\n  protoOf(AtomicRef).h1i = function () {\n    return this.kotlinx$atomicfu$value;\n  };\n  protoOf(AtomicRef).atomicfu$compareAndSet = function (expect, update) {\n    if (!(this.kotlinx$atomicfu$value === expect))\n      return false;\n    this.kotlinx$atomicfu$value = update;\n    return true;\n  };\n  protoOf(AtomicRef).atomicfu$getAndSet = function (value) {\n    var oldValue = this.kotlinx$atomicfu$value;\n    this.kotlinx$atomicfu$value = value;\n    return oldValue;\n  };\n  protoOf(AtomicRef).toString = function () {\n    return toString(this.kotlinx$atomicfu$value);\n  };\n  function atomic$ref$1(initial) {\n    return atomic$ref$(initial, None_getInstance());\n  }\n  function AtomicBoolean(value) {\n    this.kotlinx$atomicfu$value = value;\n  }\n  protoOf(AtomicBoolean).i1i = function (_set____db54di) {\n    this.kotlinx$atomicfu$value = _set____db54di;\n  };\n  protoOf(AtomicBoolean).h1i = function () {\n    return this.kotlinx$atomicfu$value;\n  };\n  protoOf(AtomicBoolean).atomicfu$compareAndSet = function (expect, update) {\n    if (!(this.kotlinx$atomicfu$value === expect))\n      return false;\n    this.kotlinx$atomicfu$value = update;\n    return true;\n  };\n  protoOf(AtomicBoolean).atomicfu$getAndSet = function (value) {\n    var oldValue = this.kotlinx$atomicfu$value;\n    this.kotlinx$atomicfu$value = value;\n    return oldValue;\n  };\n  protoOf(AtomicBoolean).toString = function () {\n    return this.kotlinx$atomicfu$value.toString();\n  };\n  function atomic$boolean$1(initial) {\n    return atomic$boolean$(initial, None_getInstance());\n  }\n  function AtomicInt(value) {\n    this.kotlinx$atomicfu$value = value;\n  }\n  protoOf(AtomicInt).j1i = function (_set____db54di) {\n    this.kotlinx$atomicfu$value = _set____db54di;\n  };\n  protoOf(AtomicInt).h1i = function () {\n    return this.kotlinx$atomicfu$value;\n  };\n  protoOf(AtomicInt).atomicfu$compareAndSet = function (expect, update) {\n    if (!(this.kotlinx$atomicfu$value === expect))\n      return false;\n    this.kotlinx$atomicfu$value = update;\n    return true;\n  };\n  protoOf(AtomicInt).atomicfu$getAndSet = function (value) {\n    var oldValue = this.kotlinx$atomicfu$value;\n    this.kotlinx$atomicfu$value = value;\n    return oldValue;\n  };\n  protoOf(AtomicInt).atomicfu$getAndIncrement = function () {\n    var tmp1 = this.kotlinx$atomicfu$value;\n    this.kotlinx$atomicfu$value = tmp1 + 1 | 0;\n    return tmp1;\n  };\n  protoOf(AtomicInt).atomicfu$getAndDecrement = function () {\n    var tmp1 = this.kotlinx$atomicfu$value;\n    this.kotlinx$atomicfu$value = tmp1 - 1 | 0;\n    return tmp1;\n  };\n  protoOf(AtomicInt).atomicfu$getAndAdd = function (delta) {\n    var oldValue = this.kotlinx$atomicfu$value;\n    this.kotlinx$atomicfu$value = this.kotlinx$atomicfu$value + delta | 0;\n    return oldValue;\n  };\n  protoOf(AtomicInt).atomicfu$addAndGet = function (delta) {\n    this.kotlinx$atomicfu$value = this.kotlinx$atomicfu$value + delta | 0;\n    return this.kotlinx$atomicfu$value;\n  };\n  protoOf(AtomicInt).atomicfu$incrementAndGet = function () {\n    this.kotlinx$atomicfu$value = this.kotlinx$atomicfu$value + 1 | 0;\n    return this.kotlinx$atomicfu$value;\n  };\n  protoOf(AtomicInt).atomicfu$decrementAndGet = function () {\n    this.kotlinx$atomicfu$value = this.kotlinx$atomicfu$value - 1 | 0;\n    return this.kotlinx$atomicfu$value;\n  };\n  protoOf(AtomicInt).toString = function () {\n    return this.kotlinx$atomicfu$value.toString();\n  };\n  function atomic$int$1(initial) {\n    return atomic$int$(initial, None_getInstance());\n  }\n  function atomic$ref$(initial, trace) {\n    trace = trace === VOID ? None_getInstance() : trace;\n    return new AtomicRef(initial);\n  }\n  function atomic$boolean$(initial, trace) {\n    trace = trace === VOID ? None_getInstance() : trace;\n    return new AtomicBoolean(initial);\n  }\n  function atomic$int$(initial, trace) {\n    trace = trace === VOID ? None_getInstance() : trace;\n    return new AtomicInt(initial);\n  }\n  //region block: exports\n  _.$_$ = _.$_$ || {};\n  _.$_$.a = atomic$boolean$1;\n  _.$_$.b = atomic$ref$1;\n  _.$_$.c = atomic$int$1;\n  //endregion\n  return _;\n}));\n\n"}}]);