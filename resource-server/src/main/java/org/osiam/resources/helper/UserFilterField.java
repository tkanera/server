package org.osiam.resources.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.SetAttribute;

import org.joda.time.format.ISODateTimeFormat;
import org.osiam.storage.entities.EmailEntity;
import org.osiam.storage.entities.EmailEntity.CanonicalEmailTypes;
import org.osiam.storage.entities.EmailEntity_;
import org.osiam.storage.entities.ImEntity_;
import org.osiam.storage.entities.MetaEntity_;
import org.osiam.storage.entities.NameEntity_;
import org.osiam.storage.entities.PhoneNumberEntity_;
import org.osiam.storage.entities.PhotoEntity_;
import org.osiam.storage.entities.UserEntity;
import org.osiam.storage.entities.UserEntity_;

enum UserFilterField implements FilterField<UserEntity> {
    EXTERNALID("externalid") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root,
                FilterConstraint constraint, String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.externalId), value, cb);
        }
    },
    META_CREATED("meta.created") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root,
                FilterConstraint constraint, String value, CriteriaBuilder cb) {
            Date date = ISODateTimeFormat.dateTimeParser().parseDateTime(value).toDate();
            return constraint.createPredicateForDateField(root.get(UserEntity_.meta).get(MetaEntity_.created),
                    date, cb);
        }
    },
    META_LASTMODIFIED("meta.lastmodified") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root,
                FilterConstraint constraint, String value, CriteriaBuilder cb) {
            Date date = ISODateTimeFormat.dateTimeParser().parseDateTime(value).toDate();
            return constraint.createPredicateForDateField(
                    root.get(UserEntity_.meta).get(MetaEntity_.lastModified),
                    date, cb);
        }
    },
    META_LOCATION("meta.location") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root,
                FilterConstraint constraint, String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.meta)
                    .get(MetaEntity_.location), value, cb);
        }
    },
    USERNAME("username") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.userName), value, cb);
        }

    },
    DISPLAYNAME("displayname") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.displayName), value, cb);
        }

    },
    NICKNAME("nickname") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.nickName), value, cb);
        }

    },
    PROFILEURL("profileurl") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.profileUrl), value, cb);
        }

    },
    TITLE("title") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.title), value, cb);
        }

    },
    PREFERREDLANGUAGE("preferredlanguage") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.preferredLanguage), value, cb);
        }

    },
    LOCALE("locale") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.locale), value, cb);
        }

    },
    TIMEZONE("timezone") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.timezone), value, cb);
        }

    },
    ACTIVE("active") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForBooleanField(root.get(UserEntity_.active), Boolean.valueOf(value),
                    cb);
        }

    },
    NAME_FORMATTED("name.formatted") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.name).get(NameEntity_.formatted),
                    value, cb);
        }

    },
    NAME_FAMILYNAME("name.familyname") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.name).get(NameEntity_.familyName),
                    value, cb);
        }

    },
    NAME_GIVENNAME("name.givenname") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.name).get(NameEntity_.givenName),
                    value, cb);
        }

    },
    NAME_MIDDLENAME("name.middlename") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(root.get(UserEntity_.name).get(NameEntity_.middleName),
                    value, cb);
        }

    },
    NAME_HONORIFICPREFIX("name.honorificprefix") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(
                    root.get(UserEntity_.name).get(NameEntity_.honorificPrefix), value, cb);
        }

    },
    NAME_HONORIFICSUFFIX("name.honorificsuffix") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            return constraint.createPredicateForStringField(
                    root.get(UserEntity_.name).get(NameEntity_.honorificSuffix), value, cb);
        }

    },
    EMAILS("emails") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {

            SetJoin<UserEntity, EmailEntity> join = createOrGetJoin("emails", root, UserEntity_.emails);
            return constraint.createPredicateForStringField(join.get(EmailEntity_.value), value, cb);
        }

    },
    EMAILS_VALUE("emails.value") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {

            SetJoin<UserEntity, EmailEntity> join = createOrGetJoin("emails", root, UserEntity_.emails);
            return constraint.createPredicateForStringField(join.get(EmailEntity_.value), value, cb);
        }

    },

    EMAILS_TYPE("emails.type") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            CanonicalEmailTypes emailType;
            if(constraint == FilterConstraint.PRESENT && (value == null || value.isEmpty())){
                emailType = null;
            }else{
                emailType = CanonicalEmailTypes.valueOf(value); 
            }
            SetJoin<UserEntity, EmailEntity> join = createOrGetJoin("emails", root, UserEntity_.emails);
            return constraint.createPredicateForEmailTypeField(join.get(EmailEntity_.type),
                    emailType, cb);
        }

    },
    EMAILS_PRIMARY("emails.primary") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {

            SetJoin<UserEntity, EmailEntity> join = createOrGetJoin("emails", root, UserEntity_.emails);
            return constraint.createPredicateForBooleanField(join.get(EmailEntity_.primary),
                    Boolean.valueOf(value), cb);
        }

    },

    PHONENUMBERS("phoneNumbers") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            SetJoin<UserEntity, EmailEntity> join = createOrGetJoin("phoneNumbers", root, UserEntity_.emails);
            return constraint.createPredicateForStringField(join.get(PhoneNumberEntity_.value), value, cb);
        }
    },

    PHONENUMBERS_VALUE("phonenumbers.value") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            SetJoin<UserEntity, EmailEntity> join = createOrGetJoin("phoneNumbers", root, UserEntity_.emails);
            return constraint.createPredicateForStringField(join.get(PhoneNumberEntity_.value), value, cb);
        }
    },

    IMS("ims") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            SetJoin<UserEntity, EmailEntity> join = createOrGetJoin("ims", root, UserEntity_.emails);
            return constraint.createPredicateForStringField(join.get(ImEntity_.value), value, cb);

        }
    },

    IMS_VALUE("ims.value") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            SetJoin<UserEntity, EmailEntity> join = createOrGetJoin("ims", root, UserEntity_.emails);
            return constraint.createPredicateForStringField(join.get(ImEntity_.value), value, cb);
        }

    },
    PHOTOS("photos") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            SetJoin<UserEntity, EmailEntity> join = createOrGetJoin("photos", root, UserEntity_.emails);
            return constraint.createPredicateForStringField(join.get(PhotoEntity_.value), value, cb);

        }
    },
    PHOTOS_VALUE("photos.value") {
        @Override
        public Predicate addFilter(AbstractQuery<Long> query, Root<UserEntity> root, FilterConstraint constraint,
                String value, CriteriaBuilder cb) {
            SetJoin<UserEntity, EmailEntity> join = createOrGetJoin("photos", root, UserEntity_.emails);
            return constraint.createPredicateForStringField(join.get(PhotoEntity_.value), value, cb);

        }
    };

    private static final Map<String, UserFilterField> stringToEnum = new HashMap<>();

    static {
        for (UserFilterField filterField : values()) {
            stringToEnum.put(filterField.toString(), filterField);
        }
    }

    private final String name;

    private UserFilterField(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static UserFilterField fromString(String name) {
        return stringToEnum.get(name);
    }

    @SuppressWarnings("unchecked")
    protected <T> SetJoin<UserEntity, T> createOrGetJoin(String alias, Root<UserEntity> root,
            SetAttribute<UserEntity, T> attribute) {
        SetJoin<UserEntity, T> join = null;
        for (Join<UserEntity, ?> currentJoin : root.getJoins()) {
            if (currentJoin.getAlias().equals(alias)) {
                join = (SetJoin<UserEntity, T>) currentJoin;
                break;
            }
        }
        if (join == null) {
            join = root.join(attribute, JoinType.LEFT);
            join.alias(alias);
        }
        return join;
    }
}