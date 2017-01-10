package com.amrit.spotnepal;

import android.app.Activity;
import android.content.Context;
import android.util.Log;


/**
 * Created by amrit on 6/23/16.
 */
public class spotCollection {

    private spot[] listOfspot;
     private static final int COUNT = 21;
    private int current;
    private int currentId;
    private Context context;
    private Activity activity;

    public spotCollection(Context ctx,Activity activity) {
        this.listOfspot = new spot[]{
                new spot(R.drawable.boating,"Boating","Enjoy a boating on a beauitful lake",28.2053020,83.9616360,"ADVENTURE TOURISM","\n" +
                        "Phewa Lake Boating is the handiest refreshment in Pokhara, but it has certainly never overdone its charm and thus changed into a ubiquitous activity. The second biggest lake of Nepal, Fewa has a pagoda styled Talbarahi temple in its middle. Row close to this holy shrine of Hindus and view this beautiful piece of architecture. The lake also bears clear refection of Machhapuchhre peak and the Annapurna Range. So float over these mighty peaks, float as you on the lake!\n" +
                        "\n" +
                        "Cost Details:>\n" +
                        "1>per hour boating with boatman per boat RS.500.\n" +
                        "2>per hour self-rowing per boat RS.450\n" +
                        "3>Across  the lake way to stupa (World Peace Pagoda) one way RS.500\n" +
                        "4>across the lake way to stupa (World Peace Pagoda) two way RS.800\n" +
                        "5>starting of lake and go back (including to Barahi temple) RS.1200\n" +
                        "6>Whole day self –rowing  RS1000.\n" +
                        "Other service package are also available.\n" +
                        "Precaution:\n" +
                        "1>Authority of the boat 4 person only.\n" +
                        "2>Every-person  have to wear life jacket (per life jacket- RS 10 & whole day RS 30)\n" +
                        "3> The children above the age 5 years will be charge.\n" +
                        "4>One sold tickets not accepted return back.\n" +
                        "5>Fewa lake conservation fee RS 10.\n",new int[]{R.drawable.boating,R.drawable.lake,R.drawable.boat2}),

                new spot(R.drawable.bungg2, "Bungy Jumping", "Water Touch Bungy an adventure tourism in Pokhara", 28.2600000,83.9600000,"ADVENTURE TOURISM","\n" +
                        "\n" +
                        "Location: Hemja, Near Tibetan Camp, Pokhara.\n" +
                        "Hight: 80 meters / 50m & 32m Cliff Steel Tower\n" +
                        "Service Available: Every Day\n" +
                        "PRICE COST PER PERSON: \n" +
                        "\tPrice: Rs 3,000/- Nepali Citizen\n" +
                        "\tPrice: Rs 6,490/- Foreigner Citizen\n" +
                        "Age limit:\n" +
                        "Above 18 years(Below 18 need the consent of their guardian,who is attendance at the site).\n" +
                        "Bungy Jump in Pokhara\n" +
                        " Addition of ‘Water touch bungy’  has boost up adventure tourism in Pokhara.The jump is made from a tower.  Bungy jumpers plunge from a height of 80 metres over an artificial pond. A boat picks them up after completing the jump and helpers unharnessed them. The bungy cord is sturdy and made with modern technology.  The place is also perfect as people can enjoy the scenery of the Himalaya and other natural beauty. You are 100% ensured on safety as all the required safety measures are taken care of. Operated by the experienced and expert bungee professionals, the heart pounding free fall from the top certainly takes your breath away.\n" +
                        "\n",new int[]{R.drawable.bungee,R.drawable.bungee3,R.drawable.bungee4,R.drawable.bungg2}),
                new spot(R.drawable.para4,"Paragliding","Paragliding an adventure tourism  to enjoy and share airspace with birds",28.2439300,83.9486250,"ADVENTURE TOURISM","DURATION\t20 TO 30 MINUTES ONLY\n" +
                        "LOCATION\t TAKE OFF POINT-SARANGKOT   \n" +
                        "\t\tLANDING POINT- KHAHARE,PAMEY ROAD\n" +
                        "PRICE\t\n" +
                        "\tNepali:\n" +
                        "\tShort Flight: Rs 6,500/- (Per Person / Apporx 25 min) \n" +
                        "\tLong Flight: Rs 9000/- (Per Person / Apporx 50 min)\n" +
                        "\tVideo & Photographs – Rs 1500/-\n" +
                        "\tForeigner:\n" +
                        "\tShort Flight: Rs 10,000/- (Per Person / Apporx 25 min) \n" +
                        "\tLong Flight: Rs 13,000/- (Per Person / Apporx 50 min) \n" +
                        "\tVideo & Photographs – Rs 2000/- Per Person\n" +
                        "\n" +
                        "TOUR DESCRIPTION\t \n" +
                        "\tParagliding in this Himalayan country can be a truly wonderful and fulfilling experience for the adventure-seekers. You can experience unparalleled scenic grandeur as you share airspace with Himalayan griffin vultures, eagles, kites, while floating over villages, monasteries, temples, lakes and jungles, with a fantastic view of the majestic himalayas.\n" +
                        "\tThe main area for flying in Nepal is the Annapurna region, more specifically the Pokhara valley. The micro-climate of this valley makes it an ideal area for flying, with far more constant conditions than the Kathmandu valley 150 km east.\n" +
                        "\n" +
                        "Paragliding Conditions:\t \n" +
                        "The most popular months are November, December and January, because of the super consistent weather during this time.(Usually after the end of monsoon) \n" +
                        "SCHEDULE FLIGHT:\n" +
                        "\t9.30 AM – 1 st Flight | 11.30 AM – 2 nd Flight | 13.30 AM – 3 rd Flight - EVERYDAY\n" +
                        "Note:\tFlying Cost and Schedule subject to be change without prior notice. Depend on Weather Condition\n" +
                        "\n",new int[]{R.drawable.para1,R.drawable.para2,R.drawable.para3,R.drawable.para4}),
                new spot(R.drawable.raft2,"Rafting","Rafting an adventrous water sport,fight with the turbulent water & irregular waves",28.2800000,83.9300000,"ADVENTURE TOURISM","\n" +
                        "Short & Sweet River Raft in Pokhara\n" +
                        "Only 30 minutes from Lakeside Pokhara, this “short & sweet” adventure offers 1 ½ hours of non-stop adrenaline filled class IV / IV+ rapids. This fairly technical river offers fantastic Annapurna mountain views, crystal clear Himalayan white water, and a beautiful gorge decorated by suspension bridges & prayer flags blowing in the wind. \n" +
                        "Name of River: Upper Seti River - POKHARA \n" +
                        "DURATION: HALF DAY RAFTING – 90 MINUTES\n" +
                        "RIVER PUT-IN-POINT: BAMBOO BRIDGE\n" +
                        "TAKE OFF POINT: DAM – BETWEEN HEMJA TIBETAN MONASTRY & SIMPANI (BINDYABASINI TEMPLE)\n" +
                        "RIVER CLASS: IV \n" +
                        "OPRATING TIME: 9.00 AM & 1.00 PM - Twice a day\n" +
                        "Seasons: \n" +
                        "October through mid-December and March through early May are the best times. During monsoon (June to September), the white water sections are dangerous, but gentler stretches are run-able.\n" +
                        "PRICE COST PER PERSON: \n" +
                        "Price: Rs 4,100/- Nepali Citizen\n" +
                        "Price: Rs 5,100/- Foreigner Citizen\n" +
                        "What you need:\n" +
                        "T-Shirt & Shorts | River Sandal | Sun Tan Lotion-Lip Cream | Towel | Personal Toiletries | Water Bottle | Sun Glass  | Half Pant.\n" +
                        "Class: 4 Very Difficult:\n" +
                        "Large rapid that require careful manoeuving. Dangerous hazards. Scouting from the shore is often necessary and rescue is usually difficult. kayakers should be able to roll. Turbulent water & large irregular waves may flip rafts. In the event of a mishap there is significant risk of loss, damage and/or injury.\n" +
                        "\n",new int[]{R.drawable.raft,R.drawable.raft2,R.drawable.raft3}),
                new spot(R.drawable.gandruk,"Treking","Explore the typical gurung communtiy village,Ghandruk and the beautiful enviorment around it",28.4600000,83.8300000,"ADVENTURE TOURISM","Trek Duration:4-5 hours of walk from Nayapul\n" +
                        "\n" +
                        "Location:\n" +
                        "Nayapul is  located 40Km north of Pokhara.\n" +
                        "\n" +
                        "Best Season:\n" +
                        "\t Sept to Dec, Feb to June\n" +
                        "(Autumn and Spring)\n" +
                        "\n" +
                        "Ghandruk Trek is popular trekking trail in the Annapurna region. Ghandruk trek takes you to the naturally embodied village of Ghandruk in western Nepal. Ghandruk is a beautiful village with Gurung settlement. We can see the old Gurung museum in Ghandruk. Ghandruk offers a great view of Annapurna south, Gangapurna, Annapurna -III, Hiunchuli and Machhapuchhre. Ghandruk trek is ideal for all kind of trekkers as it offers an easy trekking trail with no difficult climbing or ascents to the high altitude. Ghandruk Trek further entices you with rich Gurung culture and tradition of people living in this exotic place.\n" +
                        " \n" +
                        "This short trek provides a good introduction into trekking whilst also providing some wonderful close up views of both the sacred mountain Macchapuchre (Fishtail) and Annapurna South. Our destination is the beautiful and ornate village of Ghandruk, home to the Annapurna Conservation Area Project. Here you ha ve a chance to visit the project to discover at first hand the conservation work being carried out. A refreshing three days allowing an insight into mountain life. Maximum altitude 2600 mtrs.\n" +
                        "\n",new int[]{R.drawable.gandruk,R.drawable.ghandrukrek}),
                new spot(R.drawable.trek,"ABC TREK","Go on an exciting and adventurous journey to ABC",28.5978000,83.8376500,"ADVENTURE TOURISM","TRIP FACTS:\n" +
                        "Trek Duration:\n" +
                        "12 Days\n" +
                        "Difficulty\n" +
                        "Moderate\n" +
                        "Best Season:\n" +
                        "Autumn and Spring\n" +
                        "Max. Altitude :\n" +
                        "4130m\n" +
                        "Transportation:\n" +
                        "Bus, Car or Flight\n" +
                        "Trip Cost::\n" +
                        "\tDepends upon the travel agency you choose.\n" +
                        "\n" +
                        "Annapurna Base Camp Trek is one of the best legendary and classical treks in the world offering the chances to observe and explore the typical Nepali village, and traditional way of life. The Annapurna Base Camp route goes passing through spectacular and tranquil landscapes, charming Gurung and Magar villages, lush green Rhododendron, bamboo and alpine forests to the trip’s last and final destination, Annapurna Base Camp at the height of 4130m (13546ft.)\n" +
                        " Poon Hill is the best viewpoint during the trek for a sun-rise view and mountain vista. Mount Dhaulagiri, the Annapurna Range and Mount Machhapuchhre (Fishtail Mountain) to the northern part and the Butterfly Peaks in the far western part are all visible from atop Poon Hill. \n" +
                        "Finding yourself in the base giant Annapurna I (8091m) with 360 degree Mountain View is terrifically beautiful that is beyond your imagination that is an unforgettable lifetime mountain holiday expereince in Nepal.\n" +
                        "From mid-September to November (autumn) and March to May (spring) are the best seasons to trek in Nepal. June to August (monsoon) is the rainy months but this time is best for keen botanist and has clear sky sometimes. During the hike, it offers grand spectacles of blooming rhododendron flowers during month of March, April and May. The view of Mt. Dhaulagiri with the rising sun shining on it is one of the superb views of this trek..\n",new int[]{R.drawable.trek,R.drawable.trek3,R.drawable.poonhill,R.drawable.trek4}),
                new spot(R.drawable.ultra4,"Ultra Flight","Ultralight Flight is a medium to observe  the magnificient Pokhara valley along with mountain peaks",28.2000000,83.9800000,"ADVENTURE TOURISM","DURATION:>15 Min | 30 Min | 60 Min & 90 Min - FROM 6.30 AM. TO 3.30 PM.(Sunrise to Sunset Views)\n" +
                        "<b>LOCATION</b>:>The Service is available from Pokhara Airport Only(contact to any available Travel agency which have access to the service.)\n" +
                        "TOUR DESCRIPTION\t\n" +
                        "\tUltralight Flight aircraft is another medium to observe the full spectrum of the magnificent Pokhara valley along with mountain peaks and lakes at the same time. Ultralight is a very light aircraft that has no window and seating for only two persons, including a pilot and a passenger. Because of the ability to reach to close to the mountains, tourists love the experience of flying on there small machines.Ultra light aircraft take off from Pokhara and offer spectacular views of the lakes, mountains and villages. It has a unique blend of both mountain and plateau area. While flying over the Pokhara valley, you will not miss the scenic beauty of both the Himalayas and the most famous lake of Nepal, Phewa Tal. The flight will be a journey through nature. This is truly a unique experience.The choice of the Pokhara valley for Ultra-light aircraft is appropriate chiefly because of the proximity of the mountains, and the scenic lakes.\n" +
                        "Best Season:\n" +
                        "  \tEarly autumn to early spring (beginning September through June).  \n" +
                        "FLIGHT DETAILS:\n" +
                        "\n" +
                        "FLY FOR FUN: (OPEN AIRCRAFT ONLY)\t\n" +
                        "DURATION: 15 MINUTES FLIGHT\n" +
                        "COVERED: SARANGKOT, PHEWA LAKE, PEACE STUPA, POWER HOUSE, SETI RIVER & MANY MORE.....\n" +
                        "PRICE COST: USD 95/- + USD 5/- AIRPORT TAX + 13% VAT. For Foreigners\n" +
                        "PRICE COST: Nepali Nationality \n" +
                        " \t    Rs 7500/- + Rs 200/- AIRPORT TAXES.\n" +
                        "\t\tFor Photo & Video Rs 1000/- (Both Foreigner & Nepali)\n" +
                        "\n" +
                        "GLORY OF Mt. FISHTAIL: (OPEN AND CLOSED AIRCRAFT)\t\n" +
                        "DURATION: 30 MINUTES FLIGHT\n" +
                        "COVERED: Mt. FISHTAIL FOOTHILLS, NAUDANDA HILL, SARANGKOT CIRCLE & Many More...\n" +
                        "PRICE COST: USD 170/- + USD 5/- AIRPORT TAX + 13& VAT. for Foreigners\n" +
                        "PRICE COST: Nepali Nationality \n" +
                        "\tRs 13, 500/- + Rs 200/- AIRPORT TAXES.\n" +
                        "\tFor Photo & Video Rs 1000/- (Both Foreigner & Nepali)\n" +
                        "\n" +
                        "MOUNTAIN SKY TREK: (OPEN & CLOSED AIRCRAFT)\t\n" +
                        "DURATION: 60 MINUTES FLIGHT\n" +
                        "COVERED: PANORAMIC VIEW OF ANNAPURNA RANGE, Mt. FISHTAIL FOOTHILLS & Many More....\n" +
                        "PRICE COST: USD 270/- + USD 5/- AIRPORT TAX + 13% VAT for Foreigners\n" +
                        "PRICE COST: Nepali Nationality \n" +
                        "\t\tRs 23,500/- + Rs 200/- AIRPORT TAXES. \n" +
                        "\t\tFor Photo & Video Rs 1000/- (Both Foreigner & Nepali)\n" +
                        "\n" +
                        "IN HEART OF HIMALAYA: (CLOSED AIRCRAFT ONLY)\t\n" +
                        "DURATION: 90 MINUTES FLIGHT\n" +
                        "COVERED: FLYING INSIDE THE ANNAPURNA CIRCLE AND FROM MANASLU TO DHAULAGIRI Mt. RANGE.\n" +
                        "PRICE COST: USD 390/- + 5 AIRPORT TAX + 13% VAT. for Foreigners\n" +
                        "PRICE COST: For Nepali Nationality \n" +
                        "\t\tRs 31,000/- + Rs 200/- AIRPORT TAXES. \n" +
                        "\t\tFor Photo & Video Rs 1000/- (Both Foreigner & Nepali)\n" +
                        "Note:\tFlying Cost and Schedule subject to be change with or without prior notice. Depend on Weather Condition.\n",new int[]{R.drawable.ultra1,R.drawable.ultra2,R.drawable.ultra3,R.drawable.ultra4}),
                new spot(R.drawable.zip2,"Zip Flyer","Zip flyer is the newly added adventure tourism in Pokhara",28.2800000,83.9300000,"ADVENTURE TOURISM","One of the world’s most extreme Zip Flyers, Zip Flyer in Pokhara offers an amazing amalgamation of adventure, thrill and excitement with high standard safety.\n" +
                        " With the total distance of 1.80 km (1.12 mi), a vertical height of 610 m (2000 ft) and a maximum speed of 120 km/hr (75 mph), Zip Flyer in Pokhara from ZipFlyer Nepal is the world’s steepest, tallest and fastest zip line. \n" +
                        "Zip flying starts from Sarangkot village of Pokhara. This small village provides you a breathe-taking view of majestic Annapurna and Machhapuchhre Himalayan Range and the Pokhara city. The launch pad is located at the top of the village. Only two riders go at a time. After almost two minutes of thrilling Zip ride above a dense forest, you land on the village of Hemja, a Tibetan refugee camp. You can relax your nerves on multi-cuisine restaurant and visit the souvenir shop to take a moment of your journey back home.\n" +
                        "Location :\n" +
                        "Launch Pad;top of Sarangkot\n" +
                        "Landing pad: village of Hemja, a Tibetan refugee camp.\n" +
                        " Restrictions\n" +
                        "•\tThe riders must weigh between 35 and 125 kg (75 and 275 pounds) and should be above 14 years of age.\n" +
                        "•\tThe participant should not have symptoms of Acrophobia (fear when exposed to heights).\n" +
                        "•\tIndividuals with back or neck problems are discouraged.\n" +
                        "•\tPregnant women are not allowed for the ride.\n" +
                        "Zip Flyer cost in Nepal\n" +
                        "•\tFor Nepali citizens – Rs 3,890\n" +
                        "•\tFor Foreign tourists – Rs 5,890\n" +
                        "\n",new int[]{R.drawable.zip2,R.drawable.zip3,R.drawable.zip4}),
                new spot(R.drawable.begnas,"Begnas(Majhikuna)","One of the best spot in Pokhara,peaceful and beautiful enviroment around Begnas Lake",28.1700000,84.1000000,"dating","Majhikuna is one of the best place for tourist to hang out, naturally it is majhikuna have glommy environment, Begnas Lake infront of majikuna seems to be second largest lake From Pokhara and first largest of Lekhnath Municipality.\n" +
                        "\n",new int[]{R.drawable.begnas,R.drawable.begnas3,R.drawable.begnas4,R.drawable.begnaslak}),
                new spot(R.drawable.datelake,"Lakeside","Enjoy boating with your date",28.2053020,83.9616360,"dating","Lakeside is the main attraction of Pokhara, Pokhara this name is interattached with Lakeside mean that without lakeside Pokhara is Incomplete. Most of young guy and lots of tourist who come to Nepal once visit Pokhara, Lakeside.\n" +
                        "Around lak eside we can found many places suitable for dating. Temple in Lake is best place to hang out and many religious tourists also come in Temple. There are many hotels and resturant to hang out. So during the time of new year and different festival lakesides seems to have lots of international and national Tourist.\n",new int[]{R.drawable.datelake,R.drawable.lake101,R.drawable.lakesd}),
                new spot(R.drawable.datepagoda,"World Peace Stupa","Create a lovable enviornment in peace",28.2000000,83.9500000,"dating","World Peace Pagoda is a massive Buddhist stupa and is situated on top of a hill on the southern shore of Phewa lake. It has four images of Buddha facing in our directions. The pagoda is an impressive sight and its hilltop location commands great view. Besides being an impressive sight in itself, the shrine is a great vantage point which offers spectacular views of the Annapurna range and Pokhara city. You can get there by crossing the lake by boat and then hiking up the hill.",new int[]{R.drawable.datepagoda,R.drawable.stupa,R.drawable.stupa2}),
                new spot(R.drawable.fallcave,"Gupteshwor Mahadev Cave","Enjoy the view of Davis fall from the underworld",28.1910000,83.9590000,"VISITING SPOT", "Just opposite of Devi’s fall, on the other side of the road there is Gupteshwor Cave. This cave is popular for the different natural forms made from limestone deposits. Gupteswar Gupha, a sacred cave, lies 2 km from Pokhara airport on the Siddhartha Highway leading southwest from the city. The entrance is right across from Devi’s Fall and the cave is almost 3 km long. It has some big hall-size rooms and some passages where you have to crawl on all fours. This cave holds special value for Hindus since a phallic symbol of Lord Shiva is preserved here in the condition it was discovered. \n" +
                        "This Cave divided into two parts. First parts is about 40 meters long, there is a natural cave and temple of Load Shivain the first part. In this first part you are not allowed to take photos. Next parts start after temple which way goes to the down side if Devis fall, the distance about 100 meters from second entrance, from there you can see the view of Davis Fall as well as natural rocks, Electric lights are managed for the convenience of the visitor.. After the Lord Shiva temple on second parts of this Cave you are allowed to take photos. Second parts is closed during 4 month of Monsoon (June, July, August and September)\n" +
                        "Entry fee:\n" +
                        "1st part only RS.30\n" +
                        "1st and 2nd part RS.100 \n",new int[]{R.drawable.fallcave,R.drawable.gupta,R.drawable.gupta2}),
                new spot(R.drawable.mhcave1,"Mahendra Cave","Explore the  under world of Pokhara",28.271594603901246,83.98003662831115,"VISITING SPOT","Mahendra Gupha (Cave)\n" +
                        "Another  major attraction of nature’s wonders in Pokhara is the Mahendra Gupha. Mahendra Gufa, locally called Chamero Odhaar (“House of Bats”), is the large limestone cave. Shepherd boys are said to have discovered it around 1950. A two hour walk to the north of Pokhara, it is best to bring your own torch in order to see the stalactites and stalagmites, as well as the local winged residents. Visitors will be well-advised to bring their own torches to closely inspect the formations.\n" +
                        "\n" +
                        "Mahendra Cave resides in Batulechaur. Its a 10 minute drive from chipledhunga the central market place of the Pokhara city. Mahendra cave has been named as per the Late King Mahendra Bir Bikram Shah Dev. This is also one of the most demanding tourist attraction center here in pokhara. It resides in the northern settlements of the valley.\n" +
                        "\n" +
                        "This snap here features a visitor within the interior of the cave. This cave is a natural tunnel which is provided with artificial lighting systems. Bulbs are used by the cave incharge so that the visitors can visit it without any difficulty. But do take a torch light with you just in case the electricity goes off. \n" +
                        "\n" +
                        "Mahendra cave is rich for its rock types and different stones that glitters when a beam of light is striked in it. This natural formation is to see not to strike with sharp digging objects.\n" +
                        "\n" +
                        "This snap features the internal natural tunnel of the Mahendra Cave. This cave consist of big boulders of rock and the tunnel leads to different places like Kali Khola (River) but at present many of the tunnels are blocked by huge boulders of rock.\n" +
                        "\n" +
                        "\n",new int[]{R.drawable.mahendracave1,R.drawable.mhcave1,R.drawable.mhcave2}),
                new spot(R.drawable.chamre,"Bat Cave","See the home of bats",28.26751395003393,83.97580602630615,"VISITING SPOT"," A 10 minute walk from the Mahendra cave leads to Bat's Cave which is also known as the Chameri Gufa in Nepali. Pokhara has several caves and all these caves are home to the nocturnal flying Dracula known as the BAT.\n" +
                        " This cave is located a little far form the Mahendra cave in the west .This cave was also discovered by the owner of the land – a farmer in the year 2040B.S.Though the entrance is quite narrow but the inner part of the cave is wide enough. The images of the elephant tusk, gods and goddess have been craved on the inner walls of the cave.\n" +
                        "\n" +
                        "This cave is 150 meter long , 25feet high and of the shape of “U”. This cave is famous for the bats and gives shelter to more than 15 thousand bats of different species. It can be a nice picnic spot.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        " Entry fee:\n" +
                        "adult Rs 100\n" +
                        "Opening hours\n" +
                        "6am-6pm \n" +
                        "\n",new int[]{R.drawable.chamre,R.drawable.bat,R.drawable.bat1}),
                new spot(R.drawable.sarang,"Sarangkot","View the Pokhara valley from hilltop ",28.2439300,83.9486250,"VISITING SPOT","Sarangkot is the most popular tourist destination from where the tourist can enjoy the great view of the Pokhara valley and the magnificent view of the mountains. Sarangkot is only 5 km from Lake Side. Sarangkot is the highest view point for a sunrise and it is just 1592m high. Sarangkot is 5 degrees cooler than Pokhara.\n" +
                        "\n" +
                        "Sarangkot can be reached easily by 45 minutes by a taxi ride to the top and then 45 minutes hike up to the main view point. Many tourists come to Sarangkot for sunrise view and go back after few hours but it will be good if tourist stay there for one night and enjoy the view and city light of Pokhara from Sarangkot. Paragliding can also be done from Sarangkot. Tourist can also have a breakfast at one of the hotels in Sarangkot and do the small trek up to Deurali and walk down to the highway and then take the bus back to Pokhara.",new int[]{R.drawable.sarang,R.drawable.sarang2}),
                new spot(R.drawable.lake101,"Lakeside","Enjoy the exciting environment on the bank of Fewa lake",28.2053020,83.9616360,"VISITING SPOT","Phewa lake and water sports is the main tourist attraction of Pokhara city and the north shore of the lake has developed into a tourist district, commonly called Lake-Side, with hotels, restaurants and bars catering to the tourists.[9] The water from Phewa lake's outlet is used to generate electricity. The Phewa Power House is located about 1.5 km (0.93 mi) from the southern part of the Phewa lake.[10] A part of the lake is also used as commercial caged fisheries.Major Attraction[edit]\n" +
                        "Tal Barahi Temple, located at the center of Phewa Lake, is the most important religious monument of Pokhara.This two-storied pagoda is believed to be dedicated one of the Hindu god known as Vishnu. Mostly it gets crowded in the Saturdays.\n" +
                        "Baidam is the eastern banks of Phewa lake also known as Lakeside. This part contains seemingly endless strip of hotels, lodges, restaurants, bookshops and souvenir shops. This side is one of the best known tourist area of nepal. It is also the starting point of the tour to Pokhara. ",new int[]{R.drawable.lake101,R.drawable.lakecity,R.drawable.lakesd,R.drawable.fewa2}),
                new spot(R.drawable.stupa,"World Peace Stupa","Get one step closer to eternal peace",28.201075024596985,83.94507469444272,"VISITING SPOT","World Peace Pagoda is a massive Buddhist stupa and is situated on top of a hill on the southern shore of Phewa lake. It has four images of Buddha facing in our directions. The pagoda is an impressive sight and its hilltop location commands great view. Besides being an impressive sight in itself, the shrine is a great vantage point which offers spectacular views of the Annapurna range and Pokhara city. You can get there by crossing the lake by boat and then hiking up the hill.",new int[]{R.drawable.stupa2,R.drawable.pagoda,R.drawable.datepagoda,R.drawable.ultra3}),
                new spot(R.drawable.falls,"Devis Falls","Find out the hidden message in the waterfall",28.1907000,83.9587800,"VISITING SPOT","DAVIS FALLS\n" +
                        "\n" +
                        "Devi’s Fall is locally known as the Patale Chhango (Hell’s Fall).  Devi’s Fall (also known as Devin’s and David’s) is a lovely waterfall lying about two km south-west of the Pokhara airport on the Siddhartha Highway. Legend has it that a trekker (Devin, David.) was washed away by the Pardi Khola and mysteriously dis appeared down into an underground passage beneath the fall, never to be seen again. \n" +
                        "Pokhara is the only city in Nepal which is famous for water falls. As we drive by the river sides below the hills we can see several beautiful and dashing water falls flowing downhill and finally flowing to the rivers. The highway to Baglung consist of several water falls. The city itself has a beautiful waterfall within itself and it is known as Davis Fall (In Nepali: Patale chango): \n" +
                        "\n" +
                        "This is a breath taking view of Davis Fall in Chorrepatan. Chorrepatan is famous for this water fall and there is also a cave just two minutes walk from this fall. The water flowing in this fall comes from Fewa lake and the fall is worth visiting during the rainy seasons as it possesses its maximum velocity.\n" +
                        "\n" +
                        "Pokhara is famous for mountains. But this is not only the thing that has made Pokhara popular. Davis fall is also one of the main tourist attraction center here in Pokhara. Davis fall is open every day of the week for the visitors. The ticket cost is very cheap and it is worth visiting.\n" +
                        "\n" +
                        "This snap also features Davis Fall during the Winter season. During winter there is a mass decrease in the water volume of the Fewa Lake hence the Dam is closed to maintain the water volume within the Fewa Lake. This causes the Davis Fall in maximum decrement in its water volume. The corresponding snap features the rock formation made by the high velocity of the water flowing within it.\n" +
                        "\n" +
                        "This snap features the Davis fall during the rainy season. The excess water in Fewa lake needs to be drained hence the dam is open during summer and this makes Davis fall much more vigrous. Davis fall is worth visiting during the rainy and summer seasons. This high velocity of water has created different type of rock formation as shown in the above snap.\n",new int[]{R.drawable.falls,R.drawable.dev1,R.drawable.dev2,R.drawable.devisfalls}),
                new spot(R.drawable.flake,"Fewa lake","The most beautiful lake in Pokhara reflecting the mountain images",28.2053020,83.9616360,"VISITING SPOT","Phewa lake is the second largest lake of Nepal and the largest lake of Pokhara Valley. IT is situated at an altitude of 784m at the western edge of Pokhara city near Baidam. It covers an area of about 4.43 sq km with an average depth of about 8.6m, maximum water depth is 19m. The maximum water capacity of the lake is estimated to be 46 million cubic meters. It lies in the valley of Harpan Khola which feeds the lake. On the southern mouth of the lake, at the crown of Pardi River, a dam is built which has nearly doubled the water level. The water of the lake is used for irrigation and electricity generation. \n" +
                        "\n" +
                        "The lake is surrounded by Sarangkot and Kaskikot hills on the northern side. The hill running along the lake is Raniban (Queen of Forests) which is very rich in flora and fauna. Mammals like Common leopard, Bengal fox and Barking deer are common in this forest. It is a home of abundant avifauna also.\n" +
                        "\n" +
                        "The first main attraction is the pagoda style temple in the lake which is the shrine of Barahi (Water Goddess). The next main attraction is the mirror image of mount Machhapuchhre and Annapurna range on the crystal clear water of the lake. The south - eastern side of the lake offers the best view. THere is facility of canoeing and fishing. \n" +
                        "\n" +
                        "Phewa originally used to cover an area of 9 sq km but now has been reduced to 4.43 sq. km. Geologically the rocks of either side of Phewa are slate and quartzite and make anticline structure along the axis of the lake. \n" +
                        "The lake is the home of 17 species of native fish and 4 exotic species. The forest and lake is the habitat of 6 species of amphibians, 14 species of reptiles, 34 species of mammals and 104 species of birds. Out of 104 species of birds, 14 are migratory species. \n" +
                        "\n" +
                        "The lake is linked with pitched vehicle road and oating facility is available.\n" +
                        "\n" +
                        "This snap features the pagoda style temple in the lake which is the shrine of Barahi (Water Goddess)\n",new int[]{R.drawable.lake,R.drawable.lake101,R.drawable.flake}),
                new spot(R.drawable.begnaslak,"Begnas Lake","One of the beautiful lakes and the second largest one in Pokhara",28.1700000,84.1000000,"VISITING SPOT","\n" +
                        "Begnas Lake\n" +
                        "\n" +
                        "Begnas Lake, the second largest lake of Pokhara Valley is situated at an altitude of 650m. It is located in the Siswa village on the eastern part of Pokhara and is 13 km away from the Pokhara city. It covers an area of 3 sq. km. Average water depth of the lake is 9.37 and the maximum water depth is 13.84m. The capacity of the lake is 29.05 million cubic meter. It is comparatively deeper in the northern and western part in the comparision to eastern and southern part. The lake water is drained out by Khudi Khola from the western part.\n" +
                        "\n" +
                        "Geologically the area is composed of slate and phyllite zone with beds of calcareous conglomerate and gravel deposits. The vegetation here is quite different than in other parts of Pokhara because here the major species on the southern aspect of the hills on the northern part of the lake is Shorea robuista whereas the major species of the forest on the northern aspect of the hill lying on the southern part of the lake is Schima-Castonopsis. The forest is very dense on the northern and southern part of the lake whereas there is cultivation on the eastern part and fishery project on the western part.\n" +
                        "\n" +
                        "The forest is very rich in flora and fauna. The forest surrounding the lake is the habitat of more than 150 species of other birds. The northern part of the lake is best site for observing water fowls. The forest is also rich in mammals where abundant leoparts roam majestically. The pride of this forest is that it is the home of some endangered flora and fauna. You can enjoy boating and fishing in this lake. You can travel to the nearby villages by hiring the boat all the day.\n" +
                        "\n",new int[]{R.drawable.begnas,R.drawable.begnaslak,R.drawable.begnas3,R.drawable.begnas4}),
                new spot(R.drawable.rupa,"Rupa Lake","One of the  most beatuiful lake in Pokhara",28.1542160,84.1131660,"VISITING SPOT","RUPA LAKE\n" +
                        "Rupa Lake is separated by the Pachabhaiya ridge from Begnas Tal, hence, these two lakes are often called the twin lakes. Rupa Lake is situated at an altitude of about 600m and covers an area of 1.12 sq km running along from north to south. The average depth of the lake is 3.0m and maximum water depth is 4.79m. Its water holding capacity is 3.1 million cubic meter.\n",new int[]{R.drawable.rupa,R.drawable.rupalake})

        };

        this.context=ctx;
        this.activity=activity;
        current=0;
        currentId=0;


    }
    public Context getContext() {
        return context;
    }

    public int getCurrent() {
        return current;
    }

    public spot[] getListOfspot() {
        return listOfspot;
    }

    public void setListOfspot(spot[] listOfspot) {
        this.listOfspot = listOfspot;
    }

    public void setCurrent(int current) {
        int at=-1;

        for(int i=0;i<COUNT;i++){
            if(listOfspot[i].drawable==current) at=i;
        }
        this.currentId= at;
        this.current = current;
    }

    public int getCurrentId() {
        return currentId;
    }
    public spot getSpot(String string){
        spot s;
        s=null;
        for (spot sp :
                listOfspot) {
            if(sp.name.equalsIgnoreCase(string)){
                s=sp;
            }
        }
        return s;
    }

    public int next(int drawable){
        int at=-1;

        for(int i=0;i<COUNT;i++){
                  if(listOfspot[i].drawable==drawable) at=i;
        }
        if(at==-1) return -1;

        int i=at;
        int j=0;
        while (true){
            i++;
            j++;
            if(i==COUNT){i=-1;continue;}
            if(!listOfspot[i].getVisited()) return i;
            if(j>COUNT) break;
        }

        return -1;
    }

    public int prev(int drawable){
        int at=-1;

        for(int i=0;i<COUNT;i++){
            if(listOfspot[i].drawable==drawable) at=i;
        }
        if(at==-1) return -1;
        int i=at;
        int j=0;
        Log.d("AMRIT","at prev with "+i);
        if(i==-1) i=1;
        while (true){
            i--;
            j++;
            if(i<0){i=COUNT;continue;}
            Log.d("AMRIT","at prev with "+i);
            if(!(listOfspot[i].getVisited())) {
                Log.d("AMRIT","at prev returned  "+listOfspot[i].name);

                return i;
            }else if(j>COUNT+5){
                break;
            }else {

                continue;
            }
        }

        return -1;
    }


    public String getCurrentName() {
        int at=-1;

        for(int i=0;i<COUNT;i++){
            if(listOfspot[i].drawable==current) at=i;
        }
        if(at==-1) return null;
        return listOfspot[at].name;

    }
    public int getInteger(String string){
        int at=-1;
        for(int i=0;i<COUNT;i++){
            if(listOfspot[i].name.equalsIgnoreCase(string)) at=i;
        }
        return at;
    }


    public Activity getActivity() {
        return activity;
    }
}
