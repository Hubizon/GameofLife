package com.example.hubert.gameoflife.utils;

import com.example.hubert.gameoflife.education.Skill;
import com.example.hubert.gameoflife.house.Fun;
import com.example.hubert.gameoflife.house.Lodging;
import com.example.hubert.gameoflife.house.Transport;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.shop.Food;
import com.example.hubert.gameoflife.shop.Lottery;
import com.example.hubert.gameoflife.shop.Medicines;
import com.example.hubert.gameoflife.shop.Weapon;
import com.example.hubert.gameoflife.work.CriminalJob;
import com.example.hubert.gameoflife.work.Job;

public class Arrays {

    private static Skill PassPrimarySchool = new Skill("Pass Primary School", 100, 10);
    private static Skill PassSecondarySchool = new Skill("Pass Secondary School", 750, 15);
    private static Skill PassHighSchool = new Skill("Pass High School", 2500, 20);
    private static Skill GeneralTraining = new Skill("General training", 7500, 25);
    private static Skill StudyAtCollage = new Skill("Study At Collage", 12500, 30);
    private static Skill GetAMastersDegree = new Skill("Get A Master's Degree", 25000, 35);
    public static final Skill[] skillsEducationList = new Skill[] {
            PassPrimarySchool,
            PassSecondarySchool,
            PassHighSchool,
            GeneralTraining,
            StudyAtCollage,
            GetAMastersDegree,
    };

    private static Skill WeaponSkillsBeginner = new Skill("Weapon Skills Beginner", 100, 10);
    private static Skill WeaponSkillsIntermediate = new Skill("Weapon Skills Intermediate", 750, 15);
    private static Skill WeaponSkillsAdvanced = new Skill("Weapon Skills Advanced", 2500, 20);
    private static Skill ThiefSkillsBeginner = new Skill("Thief Skills Beginner", 100, 10);
    private static Skill ThiefSkillsIntermediate = new Skill("Thief Skills Intermediate", 750, 15);
    private static Skill ThiefSkillsAdvanced = new Skill("Thief Skills Advanced", 2500, 20);

    public static final Skill[] skillsCriminalList = new Skill[] {
            WeaponSkillsBeginner,
            WeaponSkillsIntermediate,
            WeaponSkillsAdvanced,
            ThiefSkillsBeginner,
            ThiefSkillsIntermediate,
            ThiefSkillsAdvanced,
    };

    public static Food[] foodList = new Food[] {
            new Food("Eat Thrashes", 0, 50, -20, 0, -15),
            new Food("Drink Water from waste water", 0, 35, -10, 0, -10),
            new Food("Drink Water", 3, 40, 10, 0, 0),
            new Food("Drink Milk", 4, 30, 20, 0, 0),
            new Food("Eat Chips", 4, 50, -5, 0, 20),
            new Food("Eat Sandwich", 5, 75, 0, 0, 0),
            new Food("Drink Coffee", 7, 20, 0, 50, 0),
            new Food("Drink Beer", 9, 50, -10, 10, 10),
            new Food("Eat Cereal with Milk", 10, 200, 5, 0, 0),
            new Food("Drink Energy Drink", 12, 40, -5, 100, 0),
            new Food("Drink Wine", 14, 40, -5, -10, 25),
            new Food("Eat Soup", 16, 100, 5, 0, 0),
            new Food("Eat Burger", 20, 125, -5, 0, 5),
            new Food("Eat Meat", 30, 200, 0, 0, 0),
            new Food("Eat Fast Food", 40, 350, -45, 0, 20),
            new Food("Eat in Restaurant", 100, 500, 0, 0, 0),
            new Food("Eat in Exclusive Restaurant", 300, 1000, 50, 0, 50),
    };

    public static Medicines[] medicinesList = new Medicines[]{
            new Medicines("Eat Tablet", 10, 50),
            new Medicines("Go to Small Clinic", 30, 125),
            new Medicines("Go to Big Clinic", 50, 200),
            new Medicines("Go to Local Doctor", 100, 350),
            new Medicines("Go to Hospital", 150, 500),
            new Medicines("Go to Private Doctor", 225, 750),
            new Medicines("Go to World Class Hospital", 300, 1000)
    };


    private static Fun GoToTheCinema = new Fun("Go to The Cinema", "Exit",15, 120);
    private static Fun OldPhone = new Fun("Old Phone", "Phone", 50, 120);
    private static Fun BlackAndWhiteTv = new Fun("Black and White TV", "TV", 150, 180);
    private static Fun WoodenPc =  new Fun("Wooden PC", "Computer",100, 150);
    private static Fun Tv = new Fun("TV", "TV", 200, 180);
    private static Fun Smartphone = new Fun("Smartphone", "Phone", 400, 180);
    private static Fun Computer = new Fun("Computer", "Computer",650, 220);
    private static Fun PlasmaTv = new Fun("Plasma TV", "TV",1000, 360);
    private static Fun ModernComputer = new Fun("Modern Computer", "Computer", 1500, 300);

    public static Fun[] funList = new Fun[]{
            GoToTheCinema,
            OldPhone,
            BlackAndWhiteTv,
            WoodenPc,
            Tv,
            Smartphone,
            Computer,
            PlasmaTv,
            ModernComputer,
    };


    public static Lottery[] lotteryList = new Lottery[]{
            new Lottery("Scratchcard", 5, 200, 10000),
            new Lottery("Lotto", 8, 1000, 2000000),
            new Lottery("SuperLotto Plus", 10, 20000, 5000000),
            new Lottery("Powerball", 12, 150, 10000000),
            new Lottery("Euro Jackpot", 14, 350, 7500000),
            new Lottery("Mega Millions", 15, 15000, 1500000),
            new Lottery("Euro Millions", 20, 10000, 10000000),
            new Lottery("Win The Life", 100, 100000, 999999999),
    };


    private static Weapon knife = new Weapon("Knife", 20);
    private static Weapon pistol = new Weapon("Pistol", 50);
    private static Weapon grenades = new Weapon("Grenades", 150);
    private static Weapon ak47 = new Weapon("AK-47", 350);
    private static Weapon bombs = new Weapon("Bombs", 600);
    private static Weapon sniperRifle = new Weapon("Sniper Rifle", 1000);
    private static Weapon rocketLauncher = new Weapon("Rocket Launcher", 2500);

    public static Weapon[] weaponList = new Weapon[]
            {
                    knife,
                    pistol,
                    grenades,
                    ak47,
                    sniperRifle,
                    bombs,
                    rocketLauncher,
            };


    static Lodging CheapFlatInTheDangerousDistrict = new Lodging("Cheap Flat in the dangerous district", 150,  -2, 100, -2);
    private static Lodging CheapFlat = new Lodging("Cheap Flat", 185, -2, 100, -2);
    private static Lodging Flat = new Lodging("Flat", 220, 0, 125, -1);
    private static Lodging House = new Lodging("House", 425, 2, 150, 3);
    private static Lodging Motel = new Lodging("Motel", 550,  1, 125, 2);
    private static Lodging Hotel1 = new Lodging("1 Star Hotel", 675,2, 125, 3);
    private static Lodging Hotel2 = new Lodging("2 Star Hotel", 800,3, 150, 3);
    private static Lodging Hotel3 = new Lodging("3 Star Hotel", 1000,4, 175, 4);
    private static Lodging Hotel4 =  new Lodging("4 Star Hotel", 1350,5, 200, 5);
    private static Lodging Hotel5 = new Lodging("5 Star Hotel", 2000,7, 225, 8);
    private static Lodging SmallHouse = new Lodging("Small House", 350,1, 135, 2);
    private static Lodging BigHouse =  new Lodging("Big House", 750,2, 175, 5);
    private static Lodging Villa = new Lodging("Villa", 2500,  8,225, 10);
    private static Lodging VeryExclusiveVilla = new Lodging("Very Exclusive Villa", 7500,10, 275, 15);
    private static Lodging SuperExclusiveAndModernVilla = new Lodging("Super Exclusive and Modern Villa", 15000, 12, 400, 25);

    public static Lodging[] lodgingList = new Lodging[]{
            CheapFlatInTheDangerousDistrict,
            CheapFlat,
            Flat,
            House,
            Motel,
            Hotel1,
            Hotel2,
            Hotel3,
            Hotel4,
            Hotel5,
            SmallHouse,
            BigHouse,
            Villa,
            VeryExclusiveVilla,
            SuperExclusiveAndModernVilla
    };


    private static Transport Boots = new Transport("Boots", 4, 9);
    private static Transport Skateboard = new Transport("Skateboard", 10, 8);
    private static Transport Bicycle = new Transport("Bicycle", 22, 7);
    private static Transport Bus = new Transport("Bus", 60, 6);
    private static Transport Motorcycle = new Transport("Motorcycle", 150, 5);
    private static Transport Car = new Transport("Car", 175, 4);
    private static Transport SportsCar = new Transport("Sports Car", 400, 3);
    private static Transport Aeroplane = new Transport("Aeroplane", 5000, 2);
    private static Transport Rocket = new Transport("Rocket", 250000, 1);
    private static Transport Teleporter = new Transport("Teleporter", 1000000, 0);

    public static Transport[] transportList = new Transport[]{
            Boots,
            Skateboard,
            Bicycle,
            Bus,
            Motorcycle,
            Car,
            SportsCar,
            Aeroplane,
            Rocket,
            Teleporter,
    };



    public static final Job[] officeJobsList = new Job[] {
            new Job("Beggar", 25, 10, 0,  0, null,null, null, null, null, null),
            new Job("Salesperson", 55, 12, 1,  0, OldPhone, null, null,null, Boots, new Skill[] { PassPrimarySchool}),
            new Job("Dustman", 65, 75, 1, 0, OldPhone, WoodenPc,null, CheapFlatInTheDangerousDistrict, Boots, new Skill[] { PassPrimarySchool}),
            new Job("Painter", 80, 100, 5,  R.string.saved_drawing_skills_key, Smartphone, WoodenPc, BlackAndWhiteTv, CheapFlat, Bicycle, new Skill[] { PassPrimarySchool, PassSecondarySchool }),
            new Job("Teacher", 100, 115, 25,  R.string.saved_communication_skills_key, Smartphone, Computer, BlackAndWhiteTv, CheapFlat, Bicycle, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool }),
            new Job("YouTuber", 125, 150, 3,  R.string.saved_recording_skills_key, Smartphone,ModernComputer, Tv, Flat, Motorcycle, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining }),
            new Job("Programmer", 145, 170, 10,  R.string.saved_programming_skills_key, Smartphone,ModernComputer, Tv, SmallHouse, Motorcycle, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining, StudyAtCollage }),
            new Job("Footballer", 180, 200, 5,  0, Smartphone,ModernComputer, Tv, SmallHouse, Car, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining, StudyAtCollage }),
            new Job("Scientist", 220, 245, 50,  0, Smartphone, ModernComputer, PlasmaTv, House, Car, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining, StudyAtCollage, GetAMastersDegree }),
            new Job("Doctor", 275, 315, 50,  0, Smartphone,ModernComputer, PlasmaTv, Hotel3, Car, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining, StudyAtCollage, GetAMastersDegree }),
            new Job("Lawyer", 350, 550,15,  R.string.saved_communication_skills_key, Smartphone,ModernComputer, PlasmaTv, BigHouse, SportsCar, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining, StudyAtCollage, GetAMastersDegree }),
            new Job("Businessman", 500, 800, 50,  R.string.saved_communication_skills_key, Smartphone, ModernComputer, PlasmaTv, Villa, SportsCar, new Skill[] { PassPrimarySchool, PassSecondarySchool, PassHighSchool, GeneralTraining, StudyAtCollage, GetAMastersDegree }),
    };

    public static CriminalJob[] criminalJobsList = new CriminalJob[] {
            new CriminalJob("Pickpocket", 40, 45,null, null, null,null, null, new Skill[] { ThiefSkillsBeginner }, 125, null),
            new CriminalJob("Thief", 55, 65, OldPhone, null, null, CheapFlat, Bicycle,  new Skill[] { ThiefSkillsBeginner, ThiefSkillsIntermediate },75, new Weapon[] { knife }),
            new CriminalJob("Drug dealer", 70, 75, Smartphone, null, null, SmallHouse, Motorcycle,  new Skill[] { ThiefSkillsBeginner, ThiefSkillsIntermediate, WeaponSkillsBeginner }, 40, new Weapon[] { knife, pistol, grenades }),
            new CriminalJob("Terrorist", 80, 85, Smartphone, WoodenPc, null, House, Car,  new Skill[] { ThiefSkillsBeginner, ThiefSkillsIntermediate, WeaponSkillsBeginner, WeaponSkillsIntermediate },25, new Weapon[] { knife, pistol, grenades, ak47, bombs }),
            new CriminalJob("Kidnap kids", 90, 100, Smartphone, Computer, BlackAndWhiteTv, BigHouse, Car,  new Skill[] { ThiefSkillsBeginner, ThiefSkillsIntermediate, WeaponSkillsBeginner, WeaponSkillsIntermediate, ThiefSkillsAdvanced }, 20, new Weapon[] { knife, pistol, grenades, ak47, bombs }),
            new CriminalJob("Mafia member", 95, 120, Smartphone, ModernComputer, Tv, BigHouse, Car,  new Skill[] { ThiefSkillsBeginner, ThiefSkillsIntermediate, WeaponSkillsBeginner, WeaponSkillsIntermediate, ThiefSkillsAdvanced }, 15, new Weapon[] { knife, pistol, grenades, ak47, bombs, sniperRifle }),
            new CriminalJob("Assassin", 125, 160, Smartphone, ModernComputer, PlasmaTv, Villa, SportsCar,  new Skill[] { ThiefSkillsBeginner, ThiefSkillsIntermediate, WeaponSkillsBeginner, WeaponSkillsIntermediate, ThiefSkillsAdvanced, WeaponSkillsAdvanced }, 10, new Weapon[] { knife, pistol, grenades, ak47, bombs, sniperRifle, rocketLauncher }),
    };
}