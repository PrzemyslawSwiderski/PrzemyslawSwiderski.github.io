"use strict";(this.webpackChunkpswidersk_page=this.webpackChunkpswidersk_page||[]).push([[6010],{6010:n=>{n.exports="(function (root, factory) {\n  if (typeof define === 'function' && define.amd)\n    define(['exports', 'react', './kotlin-kotlin-stdlib.js'], factory);\n  else if (typeof exports === 'object')\n    factory(module.exports, require('react'), require('./kotlin-kotlin-stdlib.js'));\n  else {\n    if (typeof react === 'undefined') {\n      throw new Error(\"Error loading module 'kotlin-react-core'. Its dependency 'react' was not found. Please, check whether 'react' is loaded prior to 'kotlin-react-core'.\");\n    }\n    if (typeof this['kotlin-kotlin-stdlib'] === 'undefined') {\n      throw new Error(\"Error loading module 'kotlin-react-core'. Its dependency 'kotlin-kotlin-stdlib' was not found. Please, check whether 'kotlin-kotlin-stdlib' is loaded prior to 'kotlin-react-core'.\");\n    }\n    root['kotlin-react-core'] = factory(typeof this['kotlin-react-core'] === 'undefined' ? {} : this['kotlin-react-core'], react, this['kotlin-kotlin-stdlib']);\n  }\n}(globalThis, function (_, $module$react, kotlin_kotlin) {\n  'use strict';\n  //region block: imports\n  var createElement = $module$react.createElement;\n  var Unit_instance = kotlin_kotlin.$_$.t3;\n  var VOID = kotlin_kotlin.$_$.f;\n  var toString = kotlin_kotlin.$_$.u1;\n  //endregion\n  //region block: pre-declaration\n  //endregion\n  function get_CHILD_ARRAY() {\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    return CHILD_ARRAY;\n  }\n  var CHILD_ARRAY;\n  function get_DEFAULT_KEY() {\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    return DEFAULT_KEY;\n  }\n  var DEFAULT_KEY;\n  function addChildNode(_this__u8e3s4, node) {\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    // Inline function 'react.childArray' call\n    // Inline function 'kotlin.js.asDynamic' call\n    if (!(_this__u8e3s4[get_CHILD_ARRAY()] == null)) {\n      // Inline function 'kotlin.js.asDynamic' call\n      // Inline function 'react.childArray' call\n      // Inline function 'kotlin.js.asDynamic' call\n      _this__u8e3s4[get_CHILD_ARRAY()].push(node);\n    } else {\n      // Inline function 'react.childArray' call\n      // Inline function 'kotlin.arrayOf' call\n      // Inline function 'kotlin.js.unsafeCast' call\n      // Inline function 'kotlin.js.asDynamic' call\n      var value = [node];\n      // Inline function 'kotlin.js.asDynamic' call\n      _this__u8e3s4[get_CHILD_ARRAY()] = value;\n    }\n  }\n  function addChild(_this__u8e3s4, type, props) {\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    addChildElement(_this__u8e3s4, type, props, VOID, getDefaultKey(_this__u8e3s4));\n  }\n  function addChild_0(_this__u8e3s4, type) {\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    addChildElement(_this__u8e3s4, type, VOID, VOID, getDefaultKey(_this__u8e3s4));\n  }\n  function addChild_1(_this__u8e3s4, type, block) {\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    var defaultKey = getDefaultKey(_this__u8e3s4);\n    // Inline function 'js.objects.jso' call\n    // Inline function 'kotlin.apply' call\n    // Inline function 'js.objects.jso' call\n    var this_0 = {};\n    // Inline function 'kotlin.contracts.contract' call\n    block(this_0);\n    var props = this_0;\n    addChildElement(_this__u8e3s4, type, props, getChildArray(props), defaultKey);\n  }\n  function addChild_2(_this__u8e3s4, provider, value, block) {\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    addChild_1(_this__u8e3s4, provider, addChild$lambda(value, block));\n  }\n  function addChild_3(_this__u8e3s4, context, value, block) {\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    addChild_2(_this__u8e3s4, context.Provider, value, block);\n  }\n  function addChildElement(_this__u8e3s4, type, props, children, defaultKey) {\n    props = props === VOID ? null : props;\n    children = children === VOID ? null : children;\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    var childProps_0 = childProps(props, defaultKey);\n    var tmp;\n    if (!(children == null)) {\n      tmp = createElement.apply(null, [type, childProps_0].concat([].slice.call(children.slice())));\n    } else {\n      tmp = createElement(type, childProps_0);\n    }\n    var element = tmp;\n    addChildNode(_this__u8e3s4, element);\n  }\n  function getDefaultKey(_this__u8e3s4) {\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    // Inline function 'kotlin.js.asDynamic' call\n    var key = _this__u8e3s4[get_DEFAULT_KEY()];\n    // Inline function 'kotlin.js.asDynamic' call\n    delete(_this__u8e3s4[get_DEFAULT_KEY()]);\n    return key;\n  }\n  function getChildArray(_this__u8e3s4) {\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    // Inline function 'kotlin.js.asDynamic' call\n    return _this__u8e3s4[get_CHILD_ARRAY()];\n  }\n  function childProps(props, defaultKey) {\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    if (defaultKey == null)\n      return props;\n    if (props == null) {\n      // Inline function 'js.objects.jso' call\n      // Inline function 'kotlin.apply' call\n      // Inline function 'js.objects.jso' call\n      var this_0 = {};\n      // Inline function 'kotlin.contracts.contract' call\n      // Inline function 'react.childProps.<anonymous>' call\n      this_0.key = defaultKey;\n      return this_0;\n    }\n    if (!(props.key == null))\n      return props;\n    // Inline function 'js.objects.jso' call\n    // Inline function 'kotlin.apply' call\n    // Inline function 'js.objects.jso' call\n    var this_1 = {};\n    // Inline function 'kotlin.contracts.contract' call\n    // Inline function 'react.childProps.<anonymous>' call\n    // Inline function 'react.Props.unaryPlus' call\n    Object.assign(this_1, props);\n    this_1.key = defaultKey;\n    return this_1;\n  }\n  function getChildArray_0(_this__u8e3s4) {\n    _init_properties_ChildrenBuilder_kt__gexuom();\n    // Inline function 'kotlin.js.asDynamic' call\n    return _this__u8e3s4[get_CHILD_ARRAY()];\n  }\n  function addChild$lambda($value, $block) {\n    return function ($this$addChild) {\n      $this$addChild.value = $value;\n      $block($this$addChild);\n      return Unit_instance;\n    };\n  }\n  var properties_initialized_ChildrenBuilder_kt_gby2z0;\n  function _init_properties_ChildrenBuilder_kt__gexuom() {\n    if (!properties_initialized_ChildrenBuilder_kt_gby2z0) {\n      properties_initialized_ChildrenBuilder_kt_gby2z0 = true;\n      CHILD_ARRAY = Symbol('@@child-array');\n      DEFAULT_KEY = Symbol('@@default-key');\n    }\n  }\n  function ReactNode(source) {\n    // Inline function 'react.ReactNode' call\n    // Inline function 'kotlin.js.unsafeCast' call\n    // Inline function 'kotlin.js.asDynamic' call\n    return toString(source);\n  }\n  //region block: exports\n  _.$_$ = _.$_$ || {};\n  _.$_$.a = addChildNode;\n  _.$_$.b = addChild_0;\n  _.$_$.c = addChild_1;\n  _.$_$.d = getChildArray_0;\n  _.$_$.e = getChildArray;\n  //endregion\n  return _;\n}));\n\n"}}]);