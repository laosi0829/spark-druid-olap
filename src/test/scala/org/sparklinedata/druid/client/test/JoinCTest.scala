/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sparklinedata.druid.client.test

// scalastyle:off line.size.limit
class JoinCTest extends StarSchemaBaseTest{
  /*
  * tests jcT1-4 commented out because data in quickstart/tpch/datascale1.sample
  * doesn't match druid index
  */

  /*
  cTest("jcT1",
    "select  l_linestatus, sum(ps_availqty) " +
      "from lineitem li join partsupp ps on  li.l_suppkey = ps.ps_suppkey " +
      "and li.l_partkey = ps.ps_partkey  where l_shipdate  >= '1994-01-01'  and l_shipdate <= '1997-01-01'" +
      "group by l_linestatus",
    "select  l_linestatus, sum(ps_availqty) " +
      "from lineitembase li join partsupp ps on  li.l_suppkey = ps.ps_suppkey " +
      "and li.l_partkey = ps.ps_partkey  where l_shipdate  >= '1994-01-01'  and l_shipdate <= '1997-01-01'" +
      "group by l_linestatus"
  )

  cTest("jcT2",
    "select  l_linestatus, sum(ps_availqty) " +
      "from partsupp ps join lineitem li  on  li.l_suppkey = ps.ps_suppkey " +
      "and li.l_partkey = ps.ps_partkey where l_shipdate  >= '1994-01-01'  and l_shipdate <= '1997-01-01' " +
      "group by l_linestatus",
    "select  l_linestatus, sum(ps_availqty) " +
      "from partsupp ps join lineitembase li  on  li.l_suppkey = ps.ps_suppkey " +
      "and li.l_partkey = ps.ps_partkey where l_shipdate  >= '1994-01-01'  and l_shipdate <= '1997-01-01' " +
      "group by l_linestatus"
  )

  cTest("jcT3",
    "select  l_linestatus, sum(ps_availqty) " +
      "from lineitem li join partsupp ps on  li.l_suppkey = ps.ps_suppkey " +
      "and li.l_partkey = ps.ps_partkey where l_shipdate  >= '1994-01-01'  and l_shipdate <= '1997-01-01' " +
      "group by l_linestatus",
    "select  l_linestatus, sum(ps_availqty) " +
      "from lineitembase li join partsupp ps on  li.l_suppkey = ps.ps_suppkey " +
      "and li.l_partkey = ps.ps_partkey  where l_shipdate  >= '1994-01-01'  and l_shipdate <= '1997-01-01' " +
      "group by l_linestatus"
  )

  cTest("jcT4",
    "select s_name, l_linestatus, " +
      "count(*), sum(l_extendedprice) as s " +
      "from lineitem li join supplier s on  li.l_suppkey = s.s_suppkey " +
      "where l_shipdate  >= '1994-01-01'  and l_shipdate <= '1997-01-01' " +
      "group by s_name, l_linestatus",
    "select s_name, l_linestatus, " +
      "count(*), sum(l_extendedprice) as s " +
      "from lineitembase li join supplier s on  li.l_suppkey = s.s_suppkey " +
      "where l_shipdate  >= '1994-01-01'  and l_shipdate <= '1997-01-01' " +
      "group by s_name, l_linestatus"
  )
  */

  cTest("jcT5",
    """SELECT customer.c_mktsegment AS c_mktsegment
      |FROM   (SELECT *
      |        FROM   lineitem where l_shipdate  >= '1994-01-01'  and l_shipdate <= '1997-01-01' ) lineitem
      |       JOIN (SELECT *
      |             FROM   orders) orders
      |         ON ( lineitem.l_orderkey = orders.o_orderkey )
      |       JOIN (SELECT *
      |             FROM   customer) customer
      |         ON ( orders.o_custkey = customer.c_custkey )
      |       JOIN (SELECT *
      |             FROM   custnation) custnation
      |         ON ( customer.c_nationkey = custnation.cn_nationkey )
      |       JOIN (SELECT *
      |             FROM   custregion) custregion
      |         ON ( custnation.cn_regionkey = custregion.cr_regionkey )
      |GROUP  BY customer.c_mktsegment """.stripMargin,
    """SELECT customer.c_mktsegment AS c_mktsegment
      |FROM   (SELECT *
      |        FROM   lineitembase where l_shipdate  >= '1994-01-01'  and l_shipdate <= '1997-01-01' ) lineitembase
      |       JOIN (SELECT *
      |             FROM   orders) orders
      |         ON ( lineitembase.l_orderkey = orders.o_orderkey )
      |       JOIN (SELECT *
      |             FROM   customer) customer
      |         ON ( orders.o_custkey = customer.c_custkey )
      |       JOIN (SELECT *
      |             FROM   custnation) custnation
      |         ON ( customer.c_nationkey = custnation.cn_nationkey )
      |       JOIN (SELECT *
      |             FROM   custregion) custregion
      |         ON ( custnation.cn_regionkey = custregion.cr_regionkey )
      |GROUP  BY customer.c_mktsegment """.stripMargin
  )
}
