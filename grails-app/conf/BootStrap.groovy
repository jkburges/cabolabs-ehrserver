import demographic.Person
import common.generic.PatientProxy
import ehr.Ehr
import ehr.clinical_documents.IndexDefinition

class BootStrap {

   private static String PS = System.getProperty("file.separator")
   
   def init = { servletContext ->
      
     
     println "------------------------------------------------------------------"
     println new File(".").getAbsolutePath() // Current working directory
     println "------------------------------------------------------------------"
     
     
     // TEST
     if (IndexDefinition.count() == 0)
     {
		  /*
        def ai = new com.cabolabs.archetype.ArchetypeIndexer()
        ai.index("openEHR-EHR-COMPOSITION.signos.v1")
        ai.index("openEHR-EHR-COMPOSITION.orden_de_estudio_de_laboratorio.v1")
        ai.index("openEHR-EHR-COMPOSITION.orden_de_estudios_imagenologicos.v1")
		  */
		  def ti = new com.cabolabs.archetype.OperationalTemplateIndexer()
		  ti.indexAll()
        
        //def path = "opts" + PS + "Signos.opt"
        //def signos = new File( path )
        //ti.index(signos)
     }
     // /TEST
     
     def persons = []
     if (Person.count() == 0)
     {
        persons = [
           new Person(
               firstName: 'Pablo',
               lastName: 'Pazos',
               dob: new Date(81, 9, 24),
               sex: 'M',
               idCode: '4116238-0',
               idType: 'CI',
               role: 'pat',
               uid: '11111111-1111-1111-1111-111111111111'
           ),
           new Person(
               firstName: 'Barbara',
               lastName: 'Cardozo',
               dob: new Date(87, 2, 19),
               sex: 'F',
               idCode: '1234567-0',
               idType: 'CI',
               role: 'pat',
               uid: '22222222-1111-1111-1111-111111111111'
           ),
           new Person(
               firstName: 'Carlos',
               lastName: 'Cardozo',
               dob: new Date(80, 2, 20),
               sex: 'M',
               idCode: '3453455-0',
               idType: 'CI',
               role: 'pat',
               uid: '33333333-1111-1111-1111-111111111111'
           ),
           new Person(
               firstName: 'Mario',
               lastName: 'Gomez',
               dob: new Date(64, 8, 19),
               sex: 'M',
               idCode: '5677565-0',
               idType: 'CI',
               role: 'pat',
               uid: '44444444-1111-1111-1111-111111111111'
           ),
           new Person(
               firstName: 'Carla',
               lastName: 'Martinez',
               dob: new Date(92, 1, 5),
               sex: 'F',
               idCode: '84848884-0',
               idType: 'CI',
               role: 'pat',
               uid: '55555555-1111-1111-1111-111111111111'
           )
        ]
         
        persons.each { p ->
            
           if (!p.save())
           {
              println p.errors
           }
        }
     }
     
     // Crea EHRs para los pacientes de prueba
     // Idem EhrController.createEhr
     def ehr
     persons.each { p ->
     
        if (p.role == 'pat')
        {
           ehr = new Ehr(
              ehrId: p.uid, // the ehr id is the same as the patient just to simplify testing
              subject: new PatientProxy(
                 value: p.uid
              )
           )
         
           if (!ehr.save()) println ehr.errors
        }
     }
   }
   def destroy = {
   }
}