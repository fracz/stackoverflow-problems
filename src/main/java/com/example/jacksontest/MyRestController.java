package com.example.jacksontest;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MyRestController {

    static final String JERRY_ID = "57076a6ed1c5d61930a238c5";

    public static class Whatever {
        private ObjectId notNested = new ObjectId(JERRY_ID);

        private Map<String, Object> parameters = new HashMap<String, Object>();

        public Whatever() {
            parameters.put("tom", "Cat");
            parameters.put("jerry", new ObjectId(JERRY_ID));
        }

        public ObjectId getNotNested() {
            return notNested;
        }

        public Map<String, Object> getParameters() {
            return parameters;
        }
    }

    @ResponseBody
    @RequestMapping("/")
    public Whatever method() {
        return new Whatever();
    }
}
