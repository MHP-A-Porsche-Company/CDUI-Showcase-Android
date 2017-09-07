package com.mhp.showcase.util


import com.google.gson.*
import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.block.text.TextBlock
import java.io.IOException
import java.lang.reflect.Type
import java.util.*

/**
 * Custom implementation of [JsonSerializer] and [JsonDeserializer] to support multiple
 * implementation of [com.mhp.showcase.block.BaseBlock]. It uses the property [.TYPE] to determine
 * the kind of [com.mhp.showcase.block.BaseBlock].
 */
class GsonBlockAdapter @Throws(IOException::class, ClassNotFoundException::class)
constructor() : JsonSerializer<BaseBlock>, JsonDeserializer<BaseBlock> {
    private val blockClassesByName = HashMap<String, Class<out BaseBlock>>()

    init {
        // Declare types to be supported by the deserializer
        blockClassesByName.put("Text", TextBlock::class.java)
    }

    override fun serialize(src: BaseBlock, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val result = JsonObject()
        result.add(TYPE, JsonPrimitive(src.javaClass.simpleName))
        result.add("properties", context.serialize(src, src.javaClass))
        return result
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): BaseBlock? {
        val jsonObject = json.asJsonObject
        val type = jsonObject.get(TYPE).asString
        return if (type == null || !blockClassesByName.containsKey(type)) {
            null
        } else context.deserialize<BaseBlock>(jsonObject, blockClassesByName[type])
    }

    companion object {
        private val TYPE = "type"
    }
}
