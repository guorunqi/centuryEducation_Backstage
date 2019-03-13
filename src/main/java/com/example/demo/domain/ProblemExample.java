package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class ProblemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProblemExample() {
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

        public Criteria andQuestionnaireIdIsNull() {
            addCriterion("questionnaire_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdIsNotNull() {
            addCriterion("questionnaire_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdEqualTo(String value) {
            addCriterion("questionnaire_id =", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdNotEqualTo(String value) {
            addCriterion("questionnaire_id <>", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdGreaterThan(String value) {
            addCriterion("questionnaire_id >", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdGreaterThanOrEqualTo(String value) {
            addCriterion("questionnaire_id >=", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdLessThan(String value) {
            addCriterion("questionnaire_id <", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdLessThanOrEqualTo(String value) {
            addCriterion("questionnaire_id <=", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdLike(String value) {
            addCriterion("questionnaire_id like", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdNotLike(String value) {
            addCriterion("questionnaire_id not like", value, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdIn(List<String> values) {
            addCriterion("questionnaire_id in", values, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdNotIn(List<String> values) {
            addCriterion("questionnaire_id not in", values, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdBetween(String value1, String value2) {
            addCriterion("questionnaire_id between", value1, value2, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andQuestionnaireIdNotBetween(String value1, String value2) {
            addCriterion("questionnaire_id not between", value1, value2, "questionnaireId");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeIsNull() {
            addCriterion("exhibition_type is null");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeIsNotNull() {
            addCriterion("exhibition_type is not null");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeEqualTo(String value) {
            addCriterion("exhibition_type =", value, "exhibitionType");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeNotEqualTo(String value) {
            addCriterion("exhibition_type <>", value, "exhibitionType");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeGreaterThan(String value) {
            addCriterion("exhibition_type >", value, "exhibitionType");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("exhibition_type >=", value, "exhibitionType");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeLessThan(String value) {
            addCriterion("exhibition_type <", value, "exhibitionType");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeLessThanOrEqualTo(String value) {
            addCriterion("exhibition_type <=", value, "exhibitionType");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeLike(String value) {
            addCriterion("exhibition_type like", value, "exhibitionType");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeNotLike(String value) {
            addCriterion("exhibition_type not like", value, "exhibitionType");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeIn(List<String> values) {
            addCriterion("exhibition_type in", values, "exhibitionType");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeNotIn(List<String> values) {
            addCriterion("exhibition_type not in", values, "exhibitionType");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeBetween(String value1, String value2) {
            addCriterion("exhibition_type between", value1, value2, "exhibitionType");
            return (Criteria) this;
        }

        public Criteria andExhibitionTypeNotBetween(String value1, String value2) {
            addCriterion("exhibition_type not between", value1, value2, "exhibitionType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeIsNull() {
            addCriterion("answer_type is null");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeIsNotNull() {
            addCriterion("answer_type is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeEqualTo(String value) {
            addCriterion("answer_type =", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeNotEqualTo(String value) {
            addCriterion("answer_type <>", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeGreaterThan(String value) {
            addCriterion("answer_type >", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("answer_type >=", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeLessThan(String value) {
            addCriterion("answer_type <", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeLessThanOrEqualTo(String value) {
            addCriterion("answer_type <=", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeLike(String value) {
            addCriterion("answer_type like", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeNotLike(String value) {
            addCriterion("answer_type not like", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeIn(List<String> values) {
            addCriterion("answer_type in", values, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeNotIn(List<String> values) {
            addCriterion("answer_type not in", values, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeBetween(String value1, String value2) {
            addCriterion("answer_type between", value1, value2, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeNotBetween(String value1, String value2) {
            addCriterion("answer_type not between", value1, value2, "answerType");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
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