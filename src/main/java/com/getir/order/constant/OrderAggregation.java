package com.getir.order.constant;

public class OrderAggregation {
    public static final String QUERY_GET_ORDER_STATS_BY_CUSTOMER =
            "            {'$project' : { \n" +
            "                '_id' : NumberInt(0), \n" +
            "                'orders' : '$$ROOT'\n" +
            "               }\n" +
            "             }, \n" +
            "            {'$lookup' : { \n" +
            "                'localField' : 'orders._id', \n" +
            "                'from' : 'order_details', \n" +
            "                'foreignField' : 'orderId', \n" +
            "                'as' : 'order_details'\n" +
            "            }\n" +
            "        }, \n" +
            "        {'$unwind' : { \n" +
            "                'path' : '$order_details', \n" +
            "                'preserveNullAndEmptyArrays' : false\n" +
            "            }\n" +
            "        },\n" +
            "        { \n" +
            "            '$match' : { \n" +
            "                'orders.customerId' : NumberLong(3)\n" +
            "            }\n" +
            "        },  \n" +
            "        { \n" +
            "            '$group' : { \n" +
            "                '_id' : { \n" +
            "                    $month : '$orders.orderTime'\n" +
            "                },\n" +
            "                uniqueCount: { $addToSet: '$orders._id' } , \n" +
            "                'COUNT(orders᎐_id)' : { \n" +
            "                    '$sum' : NumberInt(1)\n" +
            "                }, \n" +
            "                'SUM(order_details᎐count)' : { \n" +
            "                    '$sum' : '$order_details.count'\n" +
            "                }, \n" +
            "                'SUM(orders᎐totalOrderPrice)' : { \n" +
            "                    '$sum' : '$orders.totalOrderPrice'\n" +
            "                }\n" +
            "            }\n" +
            "        }, \n" +
            "        { \n" +
            "            $project : { \n" +
            "                _id : 1,\n" +
            "                totalOrderCount: { $size: '$uniqueCount' } ,\n" +
            "                totalBookCount : '$SUM(order_details᎐count)', \n" +
            "                totalPurchasedAmount : '$SUM(orders᎐totalOrderPrice)', \n" +
            "            }\n" +
            "        }\n" +
            "    , \n" +
            "    { \n" +
            "            '$sort' : { \n" +
            "                 '_id' : NumberInt(1)\n" +
            "            }\n" +
            "    }";
}
