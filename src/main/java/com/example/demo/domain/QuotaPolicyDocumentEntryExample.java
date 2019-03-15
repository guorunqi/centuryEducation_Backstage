package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class QuotaPolicyDocumentEntryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QuotaPolicyDocumentEntryExample() {
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

        public Criteria andPolicyDocumentEntryIdIsNull() {
            addCriterion("policy_document_entry_id is null");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdIsNotNull() {
            addCriterion("policy_document_entry_id is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdEqualTo(String value) {
            addCriterion("policy_document_entry_id =", value, "policyDocumentEntryId");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdNotEqualTo(String value) {
            addCriterion("policy_document_entry_id <>", value, "policyDocumentEntryId");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdGreaterThan(String value) {
            addCriterion("policy_document_entry_id >", value, "policyDocumentEntryId");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdGreaterThanOrEqualTo(String value) {
            addCriterion("policy_document_entry_id >=", value, "policyDocumentEntryId");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdLessThan(String value) {
            addCriterion("policy_document_entry_id <", value, "policyDocumentEntryId");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdLessThanOrEqualTo(String value) {
            addCriterion("policy_document_entry_id <=", value, "policyDocumentEntryId");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdLike(String value) {
            addCriterion("policy_document_entry_id like", value, "policyDocumentEntryId");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdNotLike(String value) {
            addCriterion("policy_document_entry_id not like", value, "policyDocumentEntryId");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdIn(List<String> values) {
            addCriterion("policy_document_entry_id in", values, "policyDocumentEntryId");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdNotIn(List<String> values) {
            addCriterion("policy_document_entry_id not in", values, "policyDocumentEntryId");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdBetween(String value1, String value2) {
            addCriterion("policy_document_entry_id between", value1, value2, "policyDocumentEntryId");
            return (Criteria) this;
        }

        public Criteria andPolicyDocumentEntryIdNotBetween(String value1, String value2) {
            addCriterion("policy_document_entry_id not between", value1, value2, "policyDocumentEntryId");
            return (Criteria) this;
        }

        public Criteria andQuotaIdIsNull() {
            addCriterion("quota_id is null");
            return (Criteria) this;
        }

        public Criteria andQuotaIdIsNotNull() {
            addCriterion("quota_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuotaIdEqualTo(String value) {
            addCriterion("quota_id =", value, "quotaId");
            return (Criteria) this;
        }

        public Criteria andQuotaIdNotEqualTo(String value) {
            addCriterion("quota_id <>", value, "quotaId");
            return (Criteria) this;
        }

        public Criteria andQuotaIdGreaterThan(String value) {
            addCriterion("quota_id >", value, "quotaId");
            return (Criteria) this;
        }

        public Criteria andQuotaIdGreaterThanOrEqualTo(String value) {
            addCriterion("quota_id >=", value, "quotaId");
            return (Criteria) this;
        }

        public Criteria andQuotaIdLessThan(String value) {
            addCriterion("quota_id <", value, "quotaId");
            return (Criteria) this;
        }

        public Criteria andQuotaIdLessThanOrEqualTo(String value) {
            addCriterion("quota_id <=", value, "quotaId");
            return (Criteria) this;
        }

        public Criteria andQuotaIdLike(String value) {
            addCriterion("quota_id like", value, "quotaId");
            return (Criteria) this;
        }

        public Criteria andQuotaIdNotLike(String value) {
            addCriterion("quota_id not like", value, "quotaId");
            return (Criteria) this;
        }

        public Criteria andQuotaIdIn(List<String> values) {
            addCriterion("quota_id in", values, "quotaId");
            return (Criteria) this;
        }

        public Criteria andQuotaIdNotIn(List<String> values) {
            addCriterion("quota_id not in", values, "quotaId");
            return (Criteria) this;
        }

        public Criteria andQuotaIdBetween(String value1, String value2) {
            addCriterion("quota_id between", value1, value2, "quotaId");
            return (Criteria) this;
        }

        public Criteria andQuotaIdNotBetween(String value1, String value2) {
            addCriterion("quota_id not between", value1, value2, "quotaId");
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