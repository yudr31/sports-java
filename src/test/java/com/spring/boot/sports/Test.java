package com.spring.boot.sports;

import com.spring.boot.sports.util.JsonUtil;

/**
 * author yuderen
 * version 2018/12/6 9:56
 */
public class Test {

    @org.junit.Test
    public void test(){
        ChannelAgentVO channelAgentVO = JsonUtil.toObject(dataStr,ChannelAgentVO.class);
        System.out.println(channelAgentVO);
        recursiveInitTotalCount(channelAgentVO);
        System.out.println(channelAgentVO);
    }

    public Integer recursiveInitTotalCount(ChannelAgentVO channelAgentVO){
        Integer count = channelAgentVO.getCount();
        if (channelAgentVO.getChildList().size() > 0){
            for (ChannelAgentVO agentVO : channelAgentVO.getChildList()){
                int childTotalCount = recursiveInitTotalCount(agentVO);
                count += childTotalCount;
                agentVO.setCount(childTotalCount);
            }
        }
        channelAgentVO.setCount(count);
        return count;
    }

    private static final String dataStr = "{\n" +
            "  \"gid\": \"root1\",\n" +
            "  \"channelNo\": \"root10001\",\n" +
            "  \"count\": 10,\n" +
            "  \"childList\": [\n" +
            "    {\n" +
            "      \"gid\": \"parent1\",\n" +
            "      \"channelNo\": \"parent0001\",\n" +
            "      \"count\": 100,\n" +
            "      \"childList\": [\n" +
            "        {\n" +
            "          \"gid\": \"child1\",\n" +
            "          \"channelNo\": \"child10001\",\n" +
            "          \"count\": 10,\n" +
            "          \"childList\": [\n" +
            "          \n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"gid\": \"child2\",\n" +
            "          \"channelNo\": \"child20002\",\n" +
            "          \"count\": 200,\n" +
            "          \"childList\": [\n" +
            "          \n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"gid\": \"parent2\",\n" +
            "      \"channelNo\": \"parent20002\",\n" +
            "      \"count\": 210,\n" +
            "      \"childList\": [\n" +
            "        {\n" +
            "          \"gid\": \"child3\",\n" +
            "          \"channelNo\": \"child30001\",\n" +
            "          \"count\": 100,\n" +
            "          \"childList\": [\n" +
            "          \n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"gid\": \"child4\",\n" +
            "          \"channelNo\": \"child40002\",\n" +
            "          \"count\": 20,\n" +
            "          \"childList\": [\n" +
            "          \n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"gid\": \"parent3\",\n" +
            "      \"channelNo\": \"parent30003\",\n" +
            "      \"count\": 310,\n" +
            "      \"childList\": [\n" +
            "        {\n" +
            "          \"gid\": \"child5\",\n" +
            "          \"channelNo\": \"child50001\",\n" +
            "          \"count\": 150,\n" +
            "          \"childList\": [\n" +
            "          \n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"gid\": \"child6\",\n" +
            "          \"channelNo\": \"child60002\",\n" +
            "          \"count\": 250,\n" +
            "          \"childList\": [\n" +
            "          \n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"gid\": \"child7\",\n" +
            "          \"channelNo\": \"child70002\",\n" +
            "          \"count\": 50,\n" +
            "          \"childList\": [\n" +
            "          \n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";


}
