package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class DictEntryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DictEntryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdIsNull() {
            addCriterion("dict_type_id is null");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdIsNotNull() {
            addCriterion("dict_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdEqualTo(String value) {
            addCriterion("dict_type_id =", value, "dictTypeId");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdNotEqualTo(String value) {
            addCriterion("dict_type_id <>", value, "dictTypeId");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdGreaterThan(String value) {
            addCriterion("dict_type_id >", value, "dictTypeId");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("dict_type_id >=", value, "dictTypeId");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdLessThan(String value) {
            addCriterion("dict_type_id <", value, "dictTypeId");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdLessThanOrEqualTo(String value) {
            addCriterion("dict_type_id <=", value, "dictTypeId");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdLike(String value) {
            addCriterion("dict_type_id like", value, "dictTypeId");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdNotLike(String value) {
            addCriterion("dict_type_id not like", value, "dictTypeId");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdIn(List<String> values) {
            addCriterion("dict_type_id in", values, "dictTypeId");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdNotIn(List<String> values) {
            addCriterion("dict_type_id not in", values, "dictTypeId");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdBetween(String value1, String value2) {
            addCriterion("dict_type_id between", value1, value2, "dictTypeId");
            return (Criteria) this;
        }

        public Criteria andDictTypeIdNotBetween(String value1, String value2) {
            addCriterion("dict_type_id not between", value1, value2, "dictTypeId");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameIsNull() {
            addCriterion("dict_type_name is null");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameIsNotNull() {
            addCriterion("dict_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameEqualTo(String value) {
            addCriterion("dict_type_name =", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameNotEqualTo(String value) {
            addCriterion("dict_type_name <>", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameGreaterThan(String value) {
            addCriterion("dict_type_name >", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("dict_type_name >=", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameLessThan(String value) {
            addCriterion("dict_type_name <", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameLessThanOrEqualTo(String value) {
            addCriterion("dict_type_name <=", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameLike(String value) {
            addCriterion("dict_type_name like", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameNotLike(String value) {
            addCriterion("dict_type_name not like", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameIn(List<String> values) {
            addCriterion("dict_type_name in", values, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameNotIn(List<String> values) {
            addCriterion("dict_type_name not in", values, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameBetween(String value1, String value2) {
            addCriterion("dict_type_name between", value1, value2, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameNotBetween(String value1, String value2) {
            addCriterion("dict_type_name not between", value1, value2, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictIdIsNull() {
            addCriterion("dict_id is null");
            return (Criteria) this;
        }

        public Criteria andDictIdIsNotNull() {
            addCriterion("dict_id is not null");
            return (Criteria) this;
        }

        public Criteria andDictIdEqualTo(String value) {
            addCriterion("dict_id =", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotEqualTo(String value) {
            addCriterion("dict_id <>", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdGreaterThan(String value) {
            addCriterion("dict_id >", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdGreaterThanOrEqualTo(String value) {
            addCriterion("dict_id >=", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdLessThan(String value) {
            addCriterion("dict_id <", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdLessThanOrEqualTo(String value) {
            addCriterion("dict_id <=", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdLike(String value) {
            addCriterion("dict_id like", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotLike(String value) {
            addCriterion("dict_id not like", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdIn(List<String> values) {
            addCriterion("dict_id in", values, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotIn(List<String> values) {
            addCriterion("dict_id not in", values, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdBetween(String value1, String value2) {
            addCriterion("dict_id between", value1, value2, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotBetween(String value1, String value2) {
            addCriterion("dict_id not between", value1, value2, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictNameIsNull() {
            addCriterion("dict_name is null");
            return (Criteria) this;
        }

        public Criteria andDictNameIsNotNull() {
            addCriterion("dict_name is not null");
            return (Criteria) this;
        }

        public Criteria andDictNameEqualTo(String value) {
            addCriterion("dict_name =", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameNotEqualTo(String value) {
            addCriterion("dict_name <>", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameGreaterThan(String value) {
            addCriterion("dict_name >", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameGreaterThanOrEqualTo(String value) {
            addCriterion("dict_name >=", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameLessThan(String value) {
            addCriterion("dict_name <", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameLessThanOrEqualTo(String value) {
            addCriterion("dict_name <=", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameLike(String value) {
            addCriterion("dict_name like", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameNotLike(String value) {
            addCriterion("dict_name not like", value, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameIn(List<String> values) {
            addCriterion("dict_name in", values, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameNotIn(List<String> values) {
            addCriterion("dict_name not in", values, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameBetween(String value1, String value2) {
            addCriterion("dict_name between", value1, value2, "dictName");
            return (Criteria) this;
        }

        public Criteria andDictNameNotBetween(String value1, String value2) {
            addCriterion("dict_name not between", value1, value2, "dictName");
            return (Criteria) this;
        }

        public Criteria andSortnoIsNull() {
            addCriterion("sortno is null");
            return (Criteria) this;
        }

        public Criteria andSortnoIsNotNull() {
            addCriterion("sortno is not null");
            return (Criteria) this;
        }

        public Criteria andSortnoEqualTo(Integer value) {
            addCriterion("sortno =", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoNotEqualTo(Integer value) {
            addCriterion("sortno <>", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoGreaterThan(Integer value) {
            addCriterion("sortno >", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("sortno >=", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoLessThan(Integer value) {
            addCriterion("sortno <", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoLessThanOrEqualTo(Integer value) {
            addCriterion("sortno <=", value, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoIn(List<Integer> values) {
            addCriterion("sortno in", values, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoNotIn(List<Integer> values) {
            addCriterion("sortno not in", values, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoBetween(Integer value1, Integer value2) {
            addCriterion("sortno between", value1, value2, "sortno");
            return (Criteria) this;
        }

        public Criteria andSortnoNotBetween(Integer value1, Integer value2) {
            addCriterion("sortno not between", value1, value2, "sortno");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}