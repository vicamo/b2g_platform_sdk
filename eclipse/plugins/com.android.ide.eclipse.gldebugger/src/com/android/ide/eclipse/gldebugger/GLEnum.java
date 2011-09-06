/*
 ** Copyright 2011, The Android Open Source Project
 **
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at
 **
 **     http://www.apache.org/licenses/LICENSE-2.0
 **
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ** See the License for the specific language governing permissions and
 ** limitations under the License.
 */

// auto generated by generate_GLEnum_java.py"

package com.android.ide.eclipse.gldebugger;

public enum GLEnum {
    GL_POINTS(0x0000),
    GL_LINES(0x0001),
    GL_LINE_LOOP(0x0002),
    GL_LINE_STRIP(0x0003),
    GL_TRIANGLES(0x0004),
    GL_TRIANGLE_STRIP(0x0005),
    GL_TRIANGLE_FAN(0x0006),
    GL_ADD(0x0104),
    GL_NEVER(0x0200),
    GL_LESS(0x0201),
    GL_EQUAL(0x0202),
    GL_LEQUAL(0x0203),
    GL_GREATER(0x0204),
    GL_NOTEQUAL(0x0205),
    GL_GEQUAL(0x0206),
    GL_ALWAYS(0x0207),
    GL_SRC_COLOR(0x0300),
    GL_ONE_MINUS_SRC_COLOR(0x0301),
    GL_SRC_ALPHA(0x0302),
    GL_ONE_MINUS_SRC_ALPHA(0x0303),
    GL_DST_ALPHA(0x0304),
    GL_ONE_MINUS_DST_ALPHA(0x0305),
    GL_DST_COLOR(0x0306),
    GL_ONE_MINUS_DST_COLOR(0x0307),
    GL_SRC_ALPHA_SATURATE(0x0308),
    GL_FRONT(0x0404),
    GL_BACK(0x0405),
    GL_FRONT_AND_BACK(0x0408),
    GL_INVALID_ENUM(0x0500),
    GL_INVALID_VALUE(0x0501),
    GL_INVALID_OPERATION(0x0502),
    GL_STACK_OVERFLOW(0x0503),
    GL_STACK_UNDERFLOW(0x0504),
    GL_OUT_OF_MEMORY(0x0505),
    GL_INVALID_FRAMEBUFFER_OPERATION(0x0506),
    GL_EXP(0x0800),
    GL_EXP2(0x0801),
    GL_CW(0x0900),
    GL_CCW(0x0901),
    GL_CURRENT_COLOR(0x0B00),
    GL_CURRENT_NORMAL(0x0B02),
    GL_CURRENT_TEXTURE_COORDS(0x0B03),
    GL_POINT_SMOOTH(0x0B10),
    GL_POINT_SIZE(0x0B11),
    GL_SMOOTH_POINT_SIZE_RANGE(0x0B12),
    GL_LINE_SMOOTH(0x0B20),
    GL_LINE_WIDTH(0x0B21),
    GL_SMOOTH_LINE_WIDTH_RANGE(0x0B22),
    GL_CULL_FACE(0x0B44),
    GL_CULL_FACE_MODE(0x0B45),
    GL_FRONT_FACE(0x0B46),
    GL_LIGHTING(0x0B50),
    GL_LIGHT_MODEL_TWO_SIDE(0x0B52),
    GL_LIGHT_MODEL_AMBIENT(0x0B53),
    GL_SHADE_MODEL(0x0B54),
    GL_COLOR_MATERIAL(0x0B57),
    GL_FOG(0x0B60),
    GL_FOG_DENSITY(0x0B62),
    GL_FOG_START(0x0B63),
    GL_FOG_END(0x0B64),
    GL_FOG_MODE(0x0B65),
    GL_FOG_COLOR(0x0B66),
    GL_DEPTH_RANGE(0x0B70),
    GL_DEPTH_TEST(0x0B71),
    GL_DEPTH_WRITEMASK(0x0B72),
    GL_DEPTH_CLEAR_VALUE(0x0B73),
    GL_DEPTH_FUNC(0x0B74),
    GL_STENCIL_TEST(0x0B90),
    GL_STENCIL_CLEAR_VALUE(0x0B91),
    GL_STENCIL_FUNC(0x0B92),
    GL_STENCIL_VALUE_MASK(0x0B93),
    GL_STENCIL_FAIL(0x0B94),
    GL_STENCIL_PASS_DEPTH_FAIL(0x0B95),
    GL_STENCIL_PASS_DEPTH_PASS(0x0B96),
    GL_STENCIL_REF(0x0B97),
    GL_STENCIL_WRITEMASK(0x0B98),
    GL_MATRIX_MODE(0x0BA0),
    GL_NORMALIZE(0x0BA1),
    GL_VIEWPORT(0x0BA2),
    GL_MODELVIEW_STACK_DEPTH(0x0BA3),
    GL_PROJECTION_STACK_DEPTH(0x0BA4),
    GL_TEXTURE_STACK_DEPTH(0x0BA5),
    GL_MODELVIEW_MATRIX(0x0BA6),
    GL_PROJECTION_MATRIX(0x0BA7),
    GL_TEXTURE_MATRIX(0x0BA8),
    GL_ALPHA_TEST(0x0BC0),
    GL_ALPHA_TEST_FUNC(0x0BC1),
    GL_ALPHA_TEST_REF(0x0BC2),
    GL_DITHER(0x0BD0),
    GL_BLEND_DST(0x0BE0),
    GL_BLEND_SRC(0x0BE1),
    GL_BLEND(0x0BE2),
    GL_LOGIC_OP_MODE(0x0BF0),
    GL_COLOR_LOGIC_OP(0x0BF2),
    GL_SCISSOR_BOX(0x0C10),
    GL_SCISSOR_TEST(0x0C11),
    GL_COLOR_CLEAR_VALUE(0x0C22),
    GL_COLOR_WRITEMASK(0x0C23),
    GL_PERSPECTIVE_CORRECTION_HINT(0x0C50),
    GL_POINT_SMOOTH_HINT(0x0C51),
    GL_LINE_SMOOTH_HINT(0x0C52),
    GL_FOG_HINT(0x0C54),
    GL_UNPACK_ALIGNMENT(0x0CF5),
    GL_PACK_ALIGNMENT(0x0D05),
    GL_ALPHA_SCALE(0x0D1C),
    GL_MAX_LIGHTS(0x0D31),
    GL_MAX_CLIP_PLANES(0x0D32),
    GL_MAX_TEXTURE_SIZE(0x0D33),
    GL_MAX_MODELVIEW_STACK_DEPTH(0x0D36),
    GL_MAX_PROJECTION_STACK_DEPTH(0x0D38),
    GL_MAX_TEXTURE_STACK_DEPTH(0x0D39),
    GL_MAX_VIEWPORT_DIMS(0x0D3A),
    GL_SUBPIXEL_BITS(0x0D50),
    GL_RED_BITS(0x0D52),
    GL_GREEN_BITS(0x0D53),
    GL_BLUE_BITS(0x0D54),
    GL_ALPHA_BITS(0x0D55),
    GL_DEPTH_BITS(0x0D56),
    GL_STENCIL_BITS(0x0D57),
    GL_TEXTURE_2D(0x0DE1),
    GL_DONT_CARE(0x1100),
    GL_FASTEST(0x1101),
    GL_NICEST(0x1102),
    GL_AMBIENT(0x1200),
    GL_DIFFUSE(0x1201),
    GL_SPECULAR(0x1202),
    GL_POSITION(0x1203),
    GL_SPOT_DIRECTION(0x1204),
    GL_SPOT_EXPONENT(0x1205),
    GL_SPOT_CUTOFF(0x1206),
    GL_CONSTANT_ATTENUATION(0x1207),
    GL_LINEAR_ATTENUATION(0x1208),
    GL_QUADRATIC_ATTENUATION(0x1209),
    GL_BYTE(0x1400),
    GL_UNSIGNED_BYTE(0x1401),
    GL_SHORT(0x1402),
    GL_UNSIGNED_SHORT(0x1403),
    GL_INT(0x1404),
    GL_UNSIGNED_INT(0x1405),
    GL_FLOAT(0x1406),
    GL_FIXED(0x140C),
    GL_CLEAR(0x1500),
    GL_AND(0x1501),
    GL_AND_REVERSE(0x1502),
    GL_COPY(0x1503),
    GL_AND_INVERTED(0x1504),
    GL_NOOP(0x1505),
    GL_XOR(0x1506),
    GL_OR(0x1507),
    GL_NOR(0x1508),
    GL_EQUIV(0x1509),
    GL_INVERT(0x150A),
    GL_OR_REVERSE(0x150B),
    GL_COPY_INVERTED(0x150C),
    GL_OR_INVERTED(0x150D),
    GL_NAND(0x150E),
    GL_SET(0x150F),
    GL_EMISSION(0x1600),
    GL_SHININESS(0x1601),
    GL_AMBIENT_AND_DIFFUSE(0x1602),
    GL_MODELVIEW(0x1700),
    GL_PROJECTION(0x1701),
    GL_TEXTURE(0x1702),
    GL_COLOR_EXT(0x1800),
    GL_DEPTH_EXT(0x1801),
    GL_STENCIL_EXT(0x1802),
    GL_STENCIL_INDEX(0x1901),
    GL_DEPTH_COMPONENT(0x1902),
    GL_ALPHA(0x1906),
    GL_RGB(0x1907),
    GL_RGBA(0x1908),
    GL_LUMINANCE(0x1909),
    GL_LUMINANCE_ALPHA(0x190A),
    GL_FLAT(0x1D00),
    GL_SMOOTH(0x1D01),
    GL_KEEP(0x1E00),
    GL_REPLACE(0x1E01),
    GL_INCR(0x1E02),
    GL_DECR(0x1E03),
    GL_VENDOR(0x1F00),
    GL_RENDERER(0x1F01),
    GL_VERSION(0x1F02),
    GL_EXTENSIONS(0x1F03),
    GL_MODULATE(0x2100),
    GL_DECAL(0x2101),
    GL_TEXTURE_ENV_MODE(0x2200),
    GL_TEXTURE_ENV_COLOR(0x2201),
    GL_TEXTURE_ENV(0x2300),
    GL_TEXTURE_GEN_MODE(0x2500),
    GL_NEAREST(0x2600),
    GL_LINEAR(0x2601),
    GL_NEAREST_MIPMAP_NEAREST(0x2700),
    GL_LINEAR_MIPMAP_NEAREST(0x2701),
    GL_NEAREST_MIPMAP_LINEAR(0x2702),
    GL_LINEAR_MIPMAP_LINEAR(0x2703),
    GL_TEXTURE_MAG_FILTER(0x2800),
    GL_TEXTURE_MIN_FILTER(0x2801),
    GL_TEXTURE_WRAP_S(0x2802),
    GL_TEXTURE_WRAP_T(0x2803),
    GL_REPEAT(0x2901),
    GL_POLYGON_OFFSET_UNITS(0x2A00),
    GL_CLIP_PLANE0(0x3000),
    GL_CLIP_PLANE1(0x3001),
    GL_CLIP_PLANE2(0x3002),
    GL_CLIP_PLANE3(0x3003),
    GL_CLIP_PLANE4(0x3004),
    GL_CLIP_PLANE5(0x3005),
    GL_LIGHT0(0x4000),
    GL_LIGHT1(0x4001),
    GL_LIGHT2(0x4002),
    GL_LIGHT3(0x4003),
    GL_LIGHT4(0x4004),
    GL_LIGHT5(0x4005),
    GL_LIGHT6(0x4006),
    GL_LIGHT7(0x4007),
    GL_COVERAGE_BUFFER_BIT_NV(0x8000),
    GL_CONSTANT_COLOR(0x8001),
    GL_ONE_MINUS_CONSTANT_COLOR(0x8002),
    GL_CONSTANT_ALPHA(0x8003),
    GL_ONE_MINUS_CONSTANT_ALPHA(0x8004),
    GL_BLEND_COLOR(0x8005),
    GL_FUNC_ADD(0x8006),
    GL_MIN_EXT(0x8007),
    GL_MAX_EXT(0x8008),
    GL_BLEND_EQUATION_RGB(0x8009),
    GL_FUNC_SUBTRACT(0x800A),
    GL_FUNC_REVERSE_SUBTRACT(0x800B),
    GL_UNSIGNED_SHORT_4_4_4_4(0x8033),
    GL_UNSIGNED_SHORT_5_5_5_1(0x8034),
    GL_POLYGON_OFFSET_FILL(0x8037),
    GL_POLYGON_OFFSET_FACTOR(0x8038),
    GL_RESCALE_NORMAL(0x803A),
    GL_RGB8(0x8051),
    GL_RGBA4(0x8056),
    GL_RGB5_A1(0x8057),
    GL_RGBA8(0x8058),
    GL_TEXTURE_BINDING_2D(0x8069),
    GL_TEXTURE_BINDING_3D(0x806A),
    GL_TEXTURE_3D(0x806F),
    GL_TEXTURE_WRAP_R(0x8072),
    GL_MAX_3D_TEXTURE_SIZE(0x8073),
    GL_VERTEX_ARRAY(0x8074),
    GL_NORMAL_ARRAY(0x8075),
    GL_COLOR_ARRAY(0x8076),
    GL_TEXTURE_COORD_ARRAY(0x8078),
    GL_VERTEX_ARRAY_SIZE(0x807A),
    GL_VERTEX_ARRAY_TYPE(0x807B),
    GL_VERTEX_ARRAY_STRIDE(0x807C),
    GL_NORMAL_ARRAY_TYPE(0x807E),
    GL_NORMAL_ARRAY_STRIDE(0x807F),
    GL_COLOR_ARRAY_SIZE(0x8081),
    GL_COLOR_ARRAY_TYPE(0x8082),
    GL_COLOR_ARRAY_STRIDE(0x8083),
    GL_TEXTURE_COORD_ARRAY_SIZE(0x8088),
    GL_TEXTURE_COORD_ARRAY_TYPE(0x8089),
    GL_TEXTURE_COORD_ARRAY_STRIDE(0x808A),
    GL_VERTEX_ARRAY_POINTER(0x808E),
    GL_NORMAL_ARRAY_POINTER(0x808F),
    GL_COLOR_ARRAY_POINTER(0x8090),
    GL_TEXTURE_COORD_ARRAY_POINTER(0x8092),
    GL_MULTISAMPLE(0x809D),
    GL_SAMPLE_ALPHA_TO_COVERAGE(0x809E),
    GL_SAMPLE_ALPHA_TO_ONE(0x809F),
    GL_SAMPLE_COVERAGE(0x80A0),
    GL_SAMPLE_BUFFERS(0x80A8),
    GL_SAMPLES(0x80A9),
    GL_SAMPLE_COVERAGE_VALUE(0x80AA),
    GL_SAMPLE_COVERAGE_INVERT(0x80AB),
    GL_BLEND_DST_RGB(0x80C8),
    GL_BLEND_SRC_RGB(0x80C9),
    GL_BLEND_DST_ALPHA(0x80CA),
    GL_BLEND_SRC_ALPHA(0x80CB),
    GL_BGRA_EXT(0x80E1),
    GL_POINT_SIZE_MIN(0x8126),
    GL_POINT_SIZE_MAX(0x8127),
    GL_POINT_FADE_THRESHOLD_SIZE(0x8128),
    GL_POINT_DISTANCE_ATTENUATION(0x8129),
    GL_CLAMP_TO_EDGE(0x812F),
    GL_GENERATE_MIPMAP(0x8191),
    GL_GENERATE_MIPMAP_HINT(0x8192),
    GL_DEPTH_COMPONENT16(0x81A5),
    GL_DEPTH_COMPONENT24(0x81A6),
    GL_DEPTH_COMPONENT32(0x81A7),
    GL_UNSIGNED_SHORT_5_6_5(0x8363),
    GL_UNSIGNED_SHORT_4_4_4_4_REV_EXT(0x8365),
    GL_UNSIGNED_SHORT_1_5_5_5_REV_EXT(0x8366),
    GL_UNSIGNED_INT_2_10_10_10_REV_EXT(0x8368),
    GL_MIRRORED_REPEAT(0x8370),
    GL_COMPRESSED_RGB_S3TC_DXT1_EXT(0x83F0),
    GL_COMPRESSED_RGBA_S3TC_DXT1_EXT(0x83F1),
    GL_ALIASED_POINT_SIZE_RANGE(0x846D),
    GL_ALIASED_LINE_WIDTH_RANGE(0x846E),
    GL_TEXTURE0(0x84C0),
    GL_TEXTURE1(0x84C1),
    GL_TEXTURE2(0x84C2),
    GL_TEXTURE3(0x84C3),
    GL_TEXTURE4(0x84C4),
    GL_TEXTURE5(0x84C5),
    GL_TEXTURE6(0x84C6),
    GL_TEXTURE7(0x84C7),
    GL_TEXTURE8(0x84C8),
    GL_TEXTURE9(0x84C9),
    GL_TEXTURE10(0x84CA),
    GL_TEXTURE11(0x84CB),
    GL_TEXTURE12(0x84CC),
    GL_TEXTURE13(0x84CD),
    GL_TEXTURE14(0x84CE),
    GL_TEXTURE15(0x84CF),
    GL_TEXTURE16(0x84D0),
    GL_TEXTURE17(0x84D1),
    GL_TEXTURE18(0x84D2),
    GL_TEXTURE19(0x84D3),
    GL_TEXTURE20(0x84D4),
    GL_TEXTURE21(0x84D5),
    GL_TEXTURE22(0x84D6),
    GL_TEXTURE23(0x84D7),
    GL_TEXTURE24(0x84D8),
    GL_TEXTURE25(0x84D9),
    GL_TEXTURE26(0x84DA),
    GL_TEXTURE27(0x84DB),
    GL_TEXTURE28(0x84DC),
    GL_TEXTURE29(0x84DD),
    GL_TEXTURE30(0x84DE),
    GL_TEXTURE31(0x84DF),
    GL_ACTIVE_TEXTURE(0x84E0),
    GL_CLIENT_ACTIVE_TEXTURE(0x84E1),
    GL_MAX_TEXTURE_UNITS(0x84E2),
    GL_SUBTRACT(0x84E7),
    GL_MAX_RENDERBUFFER_SIZE(0x84E8),
    GL_ALL_COMPLETED_NV(0x84F2),
    GL_FENCE_STATUS_NV(0x84F3),
    GL_FENCE_CONDITION_NV(0x84F4),
    GL_DEPTH_STENCIL(0x84F9),
    GL_UNSIGNED_INT_24_8(0x84FA),
    GL_MAX_TEXTURE_LOD_BIAS_EXT(0x84FD),
    GL_TEXTURE_MAX_ANISOTROPY_EXT(0x84FE),
    GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT(0x84FF),
    GL_TEXTURE_FILTER_CONTROL_EXT(0x8500),
    GL_TEXTURE_LOD_BIAS_EXT(0x8501),
    GL_INCR_WRAP(0x8507),
    GL_DECR_WRAP(0x8508),
    GL_NORMAL_MAP(0x8511),
    GL_REFLECTION_MAP(0x8512),
    GL_TEXTURE_CUBE_MAP(0x8513),
    GL_TEXTURE_BINDING_CUBE_MAP(0x8514),
    GL_TEXTURE_CUBE_MAP_POSITIVE_X(0x8515),
    GL_TEXTURE_CUBE_MAP_NEGATIVE_X(0x8516),
    GL_TEXTURE_CUBE_MAP_POSITIVE_Y(0x8517),
    GL_TEXTURE_CUBE_MAP_NEGATIVE_Y(0x8518),
    GL_TEXTURE_CUBE_MAP_POSITIVE_Z(0x8519),
    GL_TEXTURE_CUBE_MAP_NEGATIVE_Z(0x851A),
    GL_MAX_CUBE_MAP_TEXTURE_SIZE(0x851C),
    GL_COMBINE(0x8570),
    GL_COMBINE_RGB(0x8571),
    GL_COMBINE_ALPHA(0x8572),
    GL_RGB_SCALE(0x8573),
    GL_ADD_SIGNED(0x8574),
    GL_INTERPOLATE(0x8575),
    GL_CONSTANT(0x8576),
    GL_PRIMARY_COLOR(0x8577),
    GL_PREVIOUS(0x8578),
    GL_SRC0_RGB(0x8580),
    GL_SRC1_RGB(0x8581),
    GL_SRC2_RGB(0x8582),
    GL_SRC0_ALPHA(0x8588),
    GL_SRC1_ALPHA(0x8589),
    GL_SRC2_ALPHA(0x858A),
    GL_OPERAND0_RGB(0x8590),
    GL_OPERAND1_RGB(0x8591),
    GL_OPERAND2_RGB(0x8592),
    GL_OPERAND0_ALPHA(0x8598),
    GL_OPERAND1_ALPHA(0x8599),
    GL_OPERAND2_ALPHA(0x859A),
    GL_VERTEX_ARRAY_BINDING(0x85B5),
    GL_VERTEX_ATTRIB_ARRAY_ENABLED(0x8622),
    GL_VERTEX_ATTRIB_ARRAY_SIZE(0x8623),
    GL_VERTEX_ATTRIB_ARRAY_STRIDE(0x8624),
    GL_VERTEX_ATTRIB_ARRAY_TYPE(0x8625),
    GL_CURRENT_VERTEX_ATTRIB(0x8626),
    GL_VERTEX_ATTRIB_ARRAY_POINTER(0x8645),
    GL_NUM_COMPRESSED_TEXTURE_FORMATS(0x86A2),
    GL_COMPRESSED_TEXTURE_FORMATS(0x86A3),
    GL_MAX_VERTEX_UNITS(0x86A4),
    GL_WEIGHT_ARRAY_TYPE(0x86A9),
    GL_WEIGHT_ARRAY_STRIDE(0x86AA),
    GL_WEIGHT_ARRAY_SIZE(0x86AB),
    GL_WEIGHT_ARRAY_POINTER(0x86AC),
    GL_WEIGHT_ARRAY(0x86AD),
    GL_DOT3_RGB(0x86AE),
    GL_DOT3_RGBA(0x86AF),
    GL_Z400_BINARY_AMD(0x8740),
    GL_PROGRAM_BINARY_LENGTH(0x8741),
    GL_BUFFER_SIZE(0x8764),
    GL_BUFFER_USAGE(0x8765),
    GL_ATC_RGBA_INTERPOLATED_ALPHA_AMD(0x87EE),
    GL_3DC_X_AMD(0x87F9),
    GL_3DC_XY_AMD(0x87FA),
    GL_NUM_PROGRAM_BINARY_FORMATS(0x87FE),
    GL_PROGRAM_BINARY_FORMATS(0x87FF),
    GL_STENCIL_BACK_FUNC(0x8800),
    GL_STENCIL_BACK_FAIL(0x8801),
    GL_STENCIL_BACK_PASS_DEPTH_FAIL(0x8802),
    GL_STENCIL_BACK_PASS_DEPTH_PASS(0x8803),
    GL_WRITEONLY_RENDERING_QCOM(0x8823),
    GL_BLEND_EQUATION_ALPHA(0x883D),
    GL_MATRIX_PALETTE(0x8840),
    GL_MAX_PALETTE_MATRICES(0x8842),
    GL_CURRENT_PALETTE_MATRIX(0x8843),
    GL_MATRIX_INDEX_ARRAY(0x8844),
    GL_MATRIX_INDEX_ARRAY_SIZE(0x8846),
    GL_MATRIX_INDEX_ARRAY_TYPE(0x8847),
    GL_MATRIX_INDEX_ARRAY_STRIDE(0x8848),
    GL_MATRIX_INDEX_ARRAY_POINTER(0x8849),
    GL_POINT_SPRITE(0x8861),
    GL_COORD_REPLACE(0x8862),
    GL_MAX_VERTEX_ATTRIBS(0x8869),
    GL_VERTEX_ATTRIB_ARRAY_NORMALIZED(0x886A),
    GL_MAX_TEXTURE_IMAGE_UNITS(0x8872),
    GL_ARRAY_BUFFER(0x8892),
    GL_ELEMENT_ARRAY_BUFFER(0x8893),
    GL_ARRAY_BUFFER_BINDING(0x8894),
    GL_ELEMENT_ARRAY_BUFFER_BINDING(0x8895),
    GL_VERTEX_ARRAY_BUFFER_BINDING(0x8896),
    GL_NORMAL_ARRAY_BUFFER_BINDING(0x8897),
    GL_COLOR_ARRAY_BUFFER_BINDING(0x8898),
    GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING(0x889A),
    GL_WEIGHT_ARRAY_BUFFER_BINDING(0x889E),
    GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING(0x889F),
    GL_WRITE_ONLY(0x88B9),
    GL_BUFFER_ACCESS(0x88BB),
    GL_BUFFER_MAPPED(0x88BC),
    GL_BUFFER_MAP_POINTER(0x88BD),
    GL_STREAM_DRAW(0x88E0),
    GL_STATIC_DRAW(0x88E4),
    GL_DYNAMIC_DRAW(0x88E8),
    GL_DEPTH24_STENCIL8(0x88F0),
    GL_POINT_SIZE_ARRAY_TYPE(0x898A),
    GL_POINT_SIZE_ARRAY_STRIDE(0x898B),
    GL_POINT_SIZE_ARRAY_POINTER(0x898C),
    GL_MODELVIEW_MATRIX_FLOAT_AS_INT_BITS(0x898D),
    GL_PROJECTION_MATRIX_FLOAT_AS_INT_BITS(0x898E),
    GL_TEXTURE_MATRIX_FLOAT_AS_INT_BITS(0x898F),
    GL_FRAGMENT_SHADER(0x8B30),
    GL_VERTEX_SHADER(0x8B31),
    GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS(0x8B4C),
    GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS(0x8B4D),
    GL_SHADER_TYPE(0x8B4F),
    GL_FLOAT_VEC2(0x8B50),
    GL_FLOAT_VEC3(0x8B51),
    GL_FLOAT_VEC4(0x8B52),
    GL_INT_VEC2(0x8B53),
    GL_INT_VEC3(0x8B54),
    GL_INT_VEC4(0x8B55),
    GL_BOOL(0x8B56),
    GL_BOOL_VEC2(0x8B57),
    GL_BOOL_VEC3(0x8B58),
    GL_BOOL_VEC4(0x8B59),
    GL_FLOAT_MAT2(0x8B5A),
    GL_FLOAT_MAT3(0x8B5B),
    GL_FLOAT_MAT4(0x8B5C),
    GL_SAMPLER_2D(0x8B5E),
    GL_SAMPLER_3D(0x8B5F),
    GL_SAMPLER_CUBE(0x8B60),
    GL_DELETE_STATUS(0x8B80),
    GL_COMPILE_STATUS(0x8B81),
    GL_LINK_STATUS(0x8B82),
    GL_VALIDATE_STATUS(0x8B83),
    GL_INFO_LOG_LENGTH(0x8B84),
    GL_ATTACHED_SHADERS(0x8B85),
    GL_ACTIVE_UNIFORMS(0x8B86),
    GL_ACTIVE_UNIFORM_MAX_LENGTH(0x8B87),
    GL_SHADER_SOURCE_LENGTH(0x8B88),
    GL_ACTIVE_ATTRIBUTES(0x8B89),
    GL_ACTIVE_ATTRIBUTE_MAX_LENGTH(0x8B8A),
    GL_FRAGMENT_SHADER_DERIVATIVE_HINT(0x8B8B),
    GL_SHADING_LANGUAGE_VERSION(0x8B8C),
    GL_CURRENT_PROGRAM(0x8B8D),
    GL_PALETTE4_RGB8(0x8B90),
    GL_PALETTE4_RGBA8(0x8B91),
    GL_PALETTE4_R5_G6_B5(0x8B92),
    GL_PALETTE4_RGBA4(0x8B93),
    GL_PALETTE4_RGB5_A1(0x8B94),
    GL_PALETTE8_RGB8(0x8B95),
    GL_PALETTE8_RGBA8(0x8B96),
    GL_PALETTE8_R5_G6_B5(0x8B97),
    GL_PALETTE8_RGBA4(0x8B98),
    GL_PALETTE8_RGB5_A1(0x8B99),
    GL_IMPLEMENTATION_COLOR_READ_TYPE(0x8B9A),
    GL_IMPLEMENTATION_COLOR_READ_FORMAT(0x8B9B),
    GL_POINT_SIZE_ARRAY(0x8B9C),
    GL_TEXTURE_CROP_RECT(0x8B9D),
    GL_MATRIX_INDEX_ARRAY_BUFFER_BINDING(0x8B9E),
    GL_POINT_SIZE_ARRAY_BUFFER_BINDING(0x8B9F),
    GL_COUNTER_TYPE_AMD(0x8BC0),
    GL_COUNTER_RANGE_AMD(0x8BC1),
    GL_UNSIGNED_INT64_AMD(0x8BC2),
    GL_PERCENTAGE_AMD(0x8BC3),
    GL_PERFMON_RESULT_AVAILABLE_AMD(0x8BC4),
    GL_PERFMON_RESULT_SIZE_AMD(0x8BC5),
    GL_PERFMON_RESULT_AMD(0x8BC6),
    GL_TEXTURE_WIDTH_QCOM(0x8BD2),
    GL_TEXTURE_HEIGHT_QCOM(0x8BD3),
    GL_TEXTURE_DEPTH_QCOM(0x8BD4),
    GL_TEXTURE_INTERNAL_FORMAT_QCOM(0x8BD5),
    GL_TEXTURE_FORMAT_QCOM(0x8BD6),
    GL_TEXTURE_TYPE_QCOM(0x8BD7),
    GL_TEXTURE_IMAGE_VALID_QCOM(0x8BD8),
    GL_TEXTURE_NUM_LEVELS_QCOM(0x8BD9),
    GL_TEXTURE_TARGET_QCOM(0x8BDA),
    GL_TEXTURE_OBJECT_VALID_QCOM(0x8BDB),
    GL_STATE_RESTORE(0x8BDC),
    GL_COMPRESSED_RGB_PVRTC_4BPPV1_IMG(0x8C00),
    GL_COMPRESSED_RGB_PVRTC_2BPPV1_IMG(0x8C01),
    GL_COMPRESSED_RGBA_PVRTC_4BPPV1_IMG(0x8C02),
    GL_COMPRESSED_RGBA_PVRTC_2BPPV1_IMG(0x8C03),
    GL_MODULATE_COLOR_IMG(0x8C04),
    GL_RECIP_ADD_SIGNED_ALPHA_IMG(0x8C05),
    GL_TEXTURE_ALPHA_MODULATE_IMG(0x8C06),
    GL_FACTOR_ALPHA_MODULATE_IMG(0x8C07),
    GL_FRAGMENT_ALPHA_MODULATE_IMG(0x8C08),
    GL_ADD_BLEND_IMG(0x8C09),
    GL_SGX_BINARY_IMG(0x8C0A),
    GL_ATC_RGB_AMD(0x8C92),
    GL_ATC_RGBA_EXPLICIT_ALPHA_AMD(0x8C93),
    GL_STENCIL_BACK_REF(0x8CA3),
    GL_STENCIL_BACK_VALUE_MASK(0x8CA4),
    GL_STENCIL_BACK_WRITEMASK(0x8CA5),
    GL_FRAMEBUFFER_BINDING(0x8CA6),
    GL_RENDERBUFFER_BINDING(0x8CA7),
    GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE(0x8CD0),
    GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME(0x8CD1),
    GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL(0x8CD2),
    GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE(0x8CD3),
    GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_3D_ZOFFSET(0x8CD4),
    GL_FRAMEBUFFER_COMPLETE(0x8CD5),
    GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT(0x8CD6),
    GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT(0x8CD7),
    GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS(0x8CD9),
    GL_FRAMEBUFFER_INCOMPLETE_FORMATS(0x8CDA),
    GL_FRAMEBUFFER_UNSUPPORTED(0x8CDD),
    GL_COLOR_ATTACHMENT0(0x8CE0),
    GL_DEPTH_ATTACHMENT(0x8D00),
    GL_STENCIL_ATTACHMENT(0x8D20),
    GL_FRAMEBUFFER(0x8D40),
    GL_RENDERBUFFER(0x8D41),
    GL_RENDERBUFFER_WIDTH(0x8D42),
    GL_RENDERBUFFER_HEIGHT(0x8D43),
    GL_RENDERBUFFER_INTERNAL_FORMAT(0x8D44),
    GL_STENCIL_INDEX1(0x8D46),
    GL_STENCIL_INDEX4(0x8D47),
    GL_STENCIL_INDEX8(0x8D48),
    GL_RENDERBUFFER_RED_SIZE(0x8D50),
    GL_RENDERBUFFER_GREEN_SIZE(0x8D51),
    GL_RENDERBUFFER_BLUE_SIZE(0x8D52),
    GL_RENDERBUFFER_ALPHA_SIZE(0x8D53),
    GL_RENDERBUFFER_DEPTH_SIZE(0x8D54),
    GL_RENDERBUFFER_STENCIL_SIZE(0x8D55),
    GL_TEXTURE_GEN_STR(0x8D60),
    GL_HALF_FLOAT(0x8D61),
    GL_RGB565(0x8D62),
    GL_ETC1_RGB8(0x8D64),
    GL_TEXTURE_EXTERNAL(0x8D65),
    GL_SAMPLER_EXTERNAL(0x8D66),
    GL_TEXTURE_BINDING_EXTERNAL(0x8D67),
    GL_REQUIRED_TEXTURE_IMAGE_UNITS(0x8D68),
    GL_LOW_FLOAT(0x8DF0),
    GL_MEDIUM_FLOAT(0x8DF1),
    GL_HIGH_FLOAT(0x8DF2),
    GL_LOW_INT(0x8DF3),
    GL_MEDIUM_INT(0x8DF4),
    GL_HIGH_INT(0x8DF5),
    GL_UNSIGNED_INT_10_10_10_2(0x8DF6),
    GL_INT_10_10_10_2(0x8DF7),
    GL_SHADER_BINARY_FORMATS(0x8DF8),
    GL_NUM_SHADER_BINARY_FORMATS(0x8DF9),
    GL_SHADER_COMPILER(0x8DFA),
    GL_MAX_VERTEX_UNIFORM_VECTORS(0x8DFB),
    GL_MAX_VARYING_VECTORS(0x8DFC),
    GL_MAX_FRAGMENT_UNIFORM_VECTORS(0x8DFD),
    GL_DEPTH_COMPONENT16_NONLINEAR_NV(0x8E2C),
    GL_COVERAGE_COMPONENT_NV(0x8ED0),
    GL_COVERAGE_COMPONENT4_NV(0x8ED1),
    GL_COVERAGE_ATTACHMENT_NV(0x8ED2),
    GL_COVERAGE_BUFFERS_NV(0x8ED3),
    GL_COVERAGE_SAMPLES_NV(0x8ED4),
    GL_COVERAGE_ALL_FRAGMENTS_NV(0x8ED5),
    GL_COVERAGE_EDGE_FRAGMENTS_NV(0x8ED6),
    GL_COVERAGE_AUTOMATIC_NV(0x8ED7),
    GL_PERFMON_GLOBAL_MODE_QCOM(0x8FA0),
    GL_SGX_PROGRAM_BINARY_IMG(0x9130),
    GL_RENDERBUFFER_SAMPLES_IMG(0x9133),
    GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE_IMG(0x9134),
    GL_MAX_SAMPLES_IMG(0x9135),
    GL_TEXTURE_SAMPLES_IMG(0x9136),
    ;

    public final int value;
    GLEnum(final int value) {
        this.value = value;
    }

    private static final java.util.HashMap<Integer, GLEnum> reverseMap = new java.util.HashMap<Integer, GLEnum>();
    static {
        for (GLEnum e : GLEnum.values())
        reverseMap.put(e.value, e);
    }

    public static GLEnum valueOf(final int value) {
        return reverseMap.get(value);
    }
}
