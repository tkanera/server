package org.osiam.resources.provisioning

import javax.persistence.EntityManager
import javax.persistence.Query

import org.osiam.resources.exceptions.ResourceExistsException
import org.osiam.resources.exceptions.ResourceNotFoundException
import org.osiam.resources.scim.Group
import org.osiam.resources.scim.Member
import org.osiam.storage.dao.GroupDAO
import org.osiam.storage.entities.GroupEntity
import org.springframework.dao.DataIntegrityViolationException

import spock.lang.Specification

class GroupCreateTest extends Specification {
    EntityManager em = Mock(EntityManager)
    GroupDAO dao = new GroupDAO(em: em)
    def underTest = new SCIMGroupProvisioningBean(groupDAO: dao)
    def members = new HashSet()



    def "should abort when a member in group is not findable"() {
        given:
        def internalId = UUID.randomUUID()
        def query = Mock(Query)
        def queryResults = []
        members.add(new Member.Builder().setValue(internalId.toString()).build())
        def group = new Group.Builder().setMembers(members).build()
        when:
        underTest.create(group)
        then:
        1 * em.createNamedQuery("getById") >> query
        1 * query.setParameter("id", internalId);
        1 * query.getResultList() >> queryResults
        def e = thrown(ResourceNotFoundException)
        e.message == "Resource " + internalId.toString() + " not found."

    }

    def "should abort when a group already exists"() {
        given:
        def internalId = UUID.randomUUID()
        def query = Mock(Query)
        members.add(new Member.Builder().setValue(internalId.toString()).build())
        def group = new Group.Builder("displayName").setMembers(members).build()
        when:
        underTest.create(group)
        then:
        1 * em.createNamedQuery("getById") >> query
        1 * query.setParameter("id", internalId);
        1 * query.getResultList() >> { throw new DataIntegrityViolationException("moep") }
        def e = thrown(ResourceExistsException)
        e.message == "displayName already exists."
    }


    def "should create a group with known member"() {
        given:
        def internalId = UUID.randomUUID()
        def query = Mock(Query)
        members.add(new Member.Builder().setValue(internalId.toString()).build())
        def group = new Group.Builder().setMembers(members).build()
        def queryResults = [GroupEntity.fromScim(group)]
        when:
        def result = underTest.create(group)
        then:
        1 * em.createNamedQuery("getById") >> query
        1 * query.setParameter("id", internalId);
        1 * query.getResultList() >> queryResults
        result.members.size() == 1
    }

    def "should create a group without member"() {
        given:
        def group = new Group.Builder().build()
        when:
        def result = underTest.create(group)
        then:
        result.members.size() == 0
    }


}
