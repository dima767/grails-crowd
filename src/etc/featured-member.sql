select m.name name, count(*) cnt from web_impression_info wi, member m 
where wi.resource_id = m.id
and date(m.joined_on) between subdate(date(curdate()),7) and curdate()
and m.about not like '%I haven\'t had a chance to write a few words about myself yet.'
and for_which_resource = 'grailscrowd.core.Member'
and (select count(*) from grails_affiliation where member_id = m.id) > 0
and (select count(*) from project_participation where participant_id = m.id) > 0
group by name
order by cnt desc
limit 1;